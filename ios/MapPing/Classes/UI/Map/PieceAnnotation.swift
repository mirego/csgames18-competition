//
//  PieceAnnotation.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import MapKit

class PieceAnnotation: NSObject, MKAnnotation {
    let coordinate: CLLocationCoordinate2D
    let iconName: String

    init(coordinate: CLLocationCoordinate2D, iconName: String) {
        self.coordinate = coordinate
        self.iconName = iconName
    }
}
