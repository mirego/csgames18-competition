//
//  PieceAnnotationView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import MapKit

class PieceAnnotationView: MKAnnotationView {
    static let reuseIdentifier = "PieceAnnotationView"

    private let pinImage = UIImageView(image: #imageLiteral(resourceName: "icn-pin"))
    private let pieceImage = UIImageView()

    override init(annotation: MKAnnotation?, reuseIdentifier: String?) {
        super.init(annotation: annotation, reuseIdentifier: reuseIdentifier)

        addSubview(pinImage)
        addSubview(pieceImage)
        size = pinImage.size
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        pieceImage.pin.hCenter().top(11).size(CGSize(width: 22, height: 22))
    }

    func configure(pieceImageName: String) {
        pieceImage.image = UIImage(named: pieceImageName)
        setNeedsLayout()
    }
}
