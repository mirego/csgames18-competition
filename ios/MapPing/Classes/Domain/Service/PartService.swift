//
//  PartService.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation

class PartService {
    private let partsUrl = URL(string: "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json")!

    var partsObservable = Observable<[Part]>()

    func refreshParts() {
        
        // Create and initiate a data task to download the JSON from URL
        URLSession.shared.dataTask(with: partsUrl) { (data, response, error) in
            guard let data = data else { return }
            
            do {
                
                // Decode the downloaded JSON data into Part object
                let decoder = JSONDecoder()
                let data = try decoder.decode([Part].self, from: data)
                
                // Pass decoded JSON data to observer
                self.partsObservable.notify(data: data)
                
            } catch let err { print("Error: Unable to decode JSON into Part object", err) }
        }.resume()
    }
}
