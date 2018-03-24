//
//  DataHelper.swift
//  MapPing
//
//  Created by Elisa Kazan on 2018-03-24.
//  Copyright © 2018 Mirego. All rights reserved.
//

import Foundation

class DataHelper {
    
    
    
    static func getData() -> [Any] {
        let urlString = "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json"
        let url = URL(string: urlString)
        
        print(urlString)
        
        var dataArray = [Any]()
        
        do {
            let data = try Data(contentsOf: url)
            let jsonResult = try JSONSerialization.jsonObject(with: data, options: .mutableLeaves)
            if let jsonResult = jsonResult as? Array<Part> {

                for obj in data {
                    print(obj)
                }
            }
        } catch {
            print("Something went wrong")
        }
        

        return dataArray
    }
    
}
