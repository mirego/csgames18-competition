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
        
        var request = URLRequest(url: partsUrl)
        // retrieve data from URL and parse into the partsObservable observable
        URLSession.shared.dataTask(with: request) { (data, response, error) in
            if error != nil {
                print(error!.localizedDescription)
            }
            
            guard let data = data else { return }
            //Implement JSON decoding and parsing
            do {
                let partsData = try JSONDecoder().decode([Part].self, from: data)
                self.partsObservable.notify(data: partsData)
                
            } catch let jsonError {
                print(jsonError)
            }
            }.resume()
    }
}
