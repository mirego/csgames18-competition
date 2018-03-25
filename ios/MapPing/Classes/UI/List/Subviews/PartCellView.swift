//
//  PartCellView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import CoreLocation

class PartCellView: UITableViewCell {

    private let partImage = UIImageView()
    private let title = UILabel()
    private let subTitle = UILabel()
    private let coordinates = UILabel()
    private let distance = UILabel()

    override init(style: UITableViewCellStyle, reuseIdentifier: String?) {
        super.init(style: .default, reuseIdentifier: "item")

        partImage.backgroundColor = .white
        partImage.contentMode = .center
        partImage.size = CGSize(width: 66, height: 66)
        partImage.layer.cornerRadius = partImage.height / 2
        partImage.layer.borderColor = UIColor.copper.cgColor
        partImage.layer.borderWidth = 4
        addSubview(partImage)

        title.setProperties(font: .leagueSpartanBold(14), textColor: .purpleBrown)
        addSubview(title)

        subTitle.setProperties(font: .systemFont(ofSize: 12), textColor: .purpleBrown)
        addSubview(subTitle)

        coordinates.setProperties(font: .systemFont(ofSize: 12), textColor: .brownishGrey)
        addSubview(coordinates)

        distance.setProperties(font: .italicSystemFont(ofSize: 12), textColor: .brownishGrey)
        addSubview(distance)

        height = 100
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        partImage.pin.left(15).vCenter()
        title.pin.right(of: partImage, aligned: .top).marginLeft(15).marginTop(8)
        subTitle.pin.below(of: title, aligned: .left).marginTop(1)
        coordinates.pin.below(of: subTitle, aligned: .left).marginTop(5)
        distance.pin.right(of: coordinates, aligned: .center).marginLeft(6)
    }

    func configure(partImageName: String, title: String, subTitle: String, coordinates: String, distance: String) {
        partImage.image = UIImage(named: partImageName)
        self.title.setProperties(text: title, fit: true)
        self.subTitle.setProperties(text: subTitle, fit: true)
        self.coordinates.setProperties(text: coordinates, fit: true)
        self.distance.setProperties(text: distance, fit: true)
        setNeedsLayout()
    }
    
    /*
     * Utility function to take a dictionary retrieved from the
     * server, and set the view components to match the information.
     */
    func configure(dict:NSDictionary) {
        let name = dict["name"] as! String
        let type = dict["type"] as! String
        let component = dict["component"] as! String
        let lat = dict["lat"] as? Double
        let lon = dict["lon"] as? Double
        let address = dict["address"] as? String

        var description = "Unavailable"
        var distance = ""
        if lat != nil && lon != nil {
            description = "\(lat!)°N, \(lon!)°W"
            if let location = ListViewController.location {
                let here = CLLocation(latitude: lat!, longitude: lon!)
                let d = (location.distance(from: here) / 1000 * 10).rounded() / 10
                distance = "\(d)km"
            }
        } else if let address = address {
            description = address
        }
        
        self.configure(partImageName: "part-\(type)", title: name, subTitle: component, coordinates: description, distance: distance)

        setNeedsLayout()
    }
}

