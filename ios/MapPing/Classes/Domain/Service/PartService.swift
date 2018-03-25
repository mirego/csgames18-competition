//
//  PartService.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation

class PartService {
    private let partsUrl = URL(string: "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json")!
    
    
    var parts = [Part]()
    var partsObservable = Observable<[Part]>()
    
    func refreshParts() {
        JSONparser(with: partsUrl)
    }
    
    
    private func JSONparser(with URL: URL) {
        
        URLSession.shared.dataTask(with: URL) {(data, response, error) in
            guard let data = data else { return }
            
            do{
                self.parts = try JSONDecoder().decode([Part].self, from: data)
                self.partsObservable.notify(data: self.parts)
                
            } catch let jsonError{
                print("Error!", jsonError)
            }
            }.resume()
    }
    
}
