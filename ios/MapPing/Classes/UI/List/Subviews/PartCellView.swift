//
//  PartCellView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

typealias PartCell = HostCell<PartCellView, PartCellViewState, LayoutMarginsTableItemLayout>

class PartCellView: UIView {

    fileprivate let partImage = UIImageView()
    fileprivate let title = UILabel()
    fileprivate let subTitle = UILabel()
    fileprivate let coordinates = UILabel()
    fileprivate let distance = UILabel()

    init() {
        super.init(frame: .zero)

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
        heightAnchor.constraint(equalToConstant: 100.0).isActive = true
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
}

struct PartCellViewState: Equatable {
    let part: Part
    
    public static func updateView(_ view: PartCellView, state: PartCellViewState?) {
        guard let state = state else {
            view.partImage.image = nil
            view.title.text = nil
            view.subTitle.text = nil
            view.coordinates.text = nil
            view.distance.text = nil
            return
        }
        
        view.partImage.image = state.getImage()
        view.title.setProperties(text: state.part.name, fit: true)
        view.subTitle.setProperties(text: state.part.component, fit: true)
        view.distance.setProperties(text: "(0.62 km)", fit: true)
        
        if let latitude = state.part.latitude, let longitude = state.part.longitude {
            let lat_dir = latitude > 0 ? "N" : "S"
            let lon_dir = longitude > 0 ? "E" : "W"
            view.coordinates.setProperties(text: "\(abs(latitude))° \(lat_dir), \(abs(longitude))° \(lon_dir)", fit: true)
        } else {
            view.coordinates.text = nil
        }
        
        view.setNeedsLayout()
    }
    
    private func getImage() -> UIImage? {
        return UIImage(named: "part-\(part.type)")
    }
    
    public static func ==(lhs: PartCellViewState, rhs: PartCellViewState) -> Bool {
        return lhs.part == rhs.part
    }
}
