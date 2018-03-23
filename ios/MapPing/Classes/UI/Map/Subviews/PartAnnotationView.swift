//
//  PartAnnotationView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import MapKit

class PartAnnotationView: MKAnnotationView {
    static let reuseIdentifier = "PartAnnotationView"

    private let pinImage = UIImageView(image: #imageLiteral(resourceName: "icn-pin"))
    private let partImage = UIImageView()

    override init(annotation: MKAnnotation?, reuseIdentifier: String?) {
        super.init(annotation: annotation, reuseIdentifier: reuseIdentifier)

        addSubview(pinImage)
        addSubview(partImage)
        size = pinImage.size
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        partImage.pin.hCenter().top(11).size(CGSize(width: 22, height: 22))
    }

    func configure(partImageName: String) {
        partImage.image = UIImage(named: partImageName)
        setNeedsLayout()
    }
}
