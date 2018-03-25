//
//  Part.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

struct Part: Codable {
    enum CodingKeys: String, CodingKey {
        case name
        case latitude = "lat"
        case longitude = "lon"
        case component
        case notes
        case type
    }

    let name: String
    let latitude: Float?
    let longitude: Float?
    let component: String
    let notes: String?
    let type : String
}


