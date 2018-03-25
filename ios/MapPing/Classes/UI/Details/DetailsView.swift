//
//  DetailsView.swift
//  MapPing
//
//  Created by Quentin Nolan on 18-03-24.
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class DetailsView: UIView {
    
    private let partImage = UIImageView()
    private let title = UILabel()
    private let subTitle = UILabel()
    private let coordinates = UILabel()
    private let distance = UILabel()
    private let address = UILabel()
    private let notes = UILabel()
    
    init() {
        super.init(frame: .zero)
        backgroundColor = .white
        
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
        
        address.setProperties(font: .italicSystemFont(ofSize: 12), textColor: .brownishGrey)
        addSubview(address)
        
        notes.setProperties(font: .italicSystemFont(ofSize: 12), textColor: .brownishGrey)
        addSubview(notes)
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
        address.pin.below(of: partImage, aligned: .left).marginTop(8)
        notes.pin.below(of: address, aligned: .left).marginTop(8)
    }
    
    func configure(part: Part) {
        let coordinates = "\(part.latitude)° N, \(part.longitude)° W";
        
        partImage.image = UIImage(named: "part-\(part.type)")
        self.title.setProperties(text: part.name, fit: true)
        self.subTitle.setProperties(text: part.component, fit: true)
        self.coordinates.setProperties(text: coordinates, fit: true)
        self.distance.setProperties(text: "(0.62 km)", fit: true)
        self.coordinates.setProperties(text: coordinates, fit: true)
        self.address.setProperties(text: part.address, fit: true)
        self.notes.setProperties(text: part.notes, fit: true)
    }
}
