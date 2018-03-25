//
//  Part.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

struct Part: Codable {
    enum CodingKeys: String, CodingKey {
        case name
        case notes
        case type
        case address
        case latitude = "lat"
        case longitude = "lon"
    }
    // add all needed attributes 
    let name: String
    let notes: String
    let type: String
    let address: String?
    let latitude: Float?
    let longitude: Float?
}
