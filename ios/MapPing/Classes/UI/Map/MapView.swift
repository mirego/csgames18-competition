//
//  MapView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import MapKit

class MapView: UIView {
    private let mapView = MKMapView()

    init() {
        super.init(frame: .zero)

        addSubview(mapView)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        mapView.pin.all()
    }
}

