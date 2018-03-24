//
//  DataHelper.swift
//  MapPing
//
//  Created by Elisa Kazan on 2018-03-24.
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation

class DataHelper {
    let urlString = "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json"
    
    
    
    func getData() -> [Part] {
        var dataArray = [Part(name: "Waddup", latitude: 46.7906725, longitude: -71.2868071, type: "sink", notes: "Not real", address: "78 Penfield Drive", component: "Identification de l'équipage")]
        let url = NSURL(string: urlString)
        
        return dataArray
    }
    
}
