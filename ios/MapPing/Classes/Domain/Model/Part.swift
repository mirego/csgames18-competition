//
//  Part.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

struct Part: Codable {
    enum CodingKeys: String, CodingKey {
        case name
        case latitude = "lat"
        case longitude = "lon"
        case type = "type"
        case description = "component"
    }

    let name: String
    let latitude: Double?
    let longitude: Double?
    let type: String
    let description: String
    
}
