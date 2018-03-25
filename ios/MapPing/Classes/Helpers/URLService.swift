//
//  URLService.swift
//  MapPing
//
//  Created by Nicholas Mercier on 2018-03-24.
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation

open class URLService {
    
    /*
     * Get the JSON file from the server, and callback with an
     * array of NSDictionary(s).
     */
    open class func getData(completion: @escaping (_ data:Array<NSDictionary>)->()){
        
        guard let url = URL(string: "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json") else { return }
        
        URLSession.shared.dataTask(with: url){ data, response, error in
            
            guard let data = data else {
                print("Error 1")
                return
            }
            
            do {
                let arr = try JSONSerialization.jsonObject(with: data) as! Array<NSDictionary>
                DispatchQueue.main.async { completion(arr) }
            } catch let error as NSError {
                print(error)
            }
            
        }.resume()
        
    }
    
}
