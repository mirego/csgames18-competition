//
//  PartAnnotation.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import MapKit

class PartAnnotation: NSObject, MKAnnotation {
    let coordinate: CLLocationCoordinate2D
    let iconName: String
    let title: String?      // Name
    let subtitle: String?   // Component

    init(coordinate: CLLocationCoordinate2D, iconName: String, title: String, subtitle: String) {
        self.coordinate = coordinate
        self.iconName = iconName
        self.title = title
        self.subtitle = subtitle
    }
}
