//
//  DataHelper.swift
//  MapPing
//
//  Created by Elisa Kazan on 2018-03-24.
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation

class DataHelper {
    static let DATA_URL = "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json"

    static func getData(completionHandler: @escaping ([Part]) -> Void) {
        let sessionConfig = URLSessionConfiguration.default
        let session = URLSession(configuration: sessionConfig)
        let url : URL! = URL(string: DATA_URL)
        
        session.dataTask(with: url, completionHandler: { (data, response, error) in
            guard let data = data else { print("Error!!"); return }
            
            do {
                let parts = try JSONDecoder().decode([Part].self, from: data)
                
                completionHandler(parts)
            } catch {
                print("Error while deserializing: \(error)")
            }
        }).resume()
    }
    
}
