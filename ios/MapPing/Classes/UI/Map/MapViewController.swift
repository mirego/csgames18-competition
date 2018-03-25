//
//  MapViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import MapKit

class MapViewController: BaseViewController {
    
    //private let quebecCityCoordinate = CLLocationCoordinate2D(latitude: 46.780904, longitude: -71.277222)
    private let startSpan = MKCoordinateSpan(latitudeDelta: 0.3, longitudeDelta: 0.3)
    
    var partService: PartService

    private var mainView: MapView {
        return self.view as! MapView
    }
    
    init(partService: PartService) {
        self.partService = partService
        super.init(nibName: nil, bundle: nil)
        navigationIcon = #imageLiteral(resourceName: "icn-map")
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        view = MapView()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Mirego has hardcoded this point for us as a reference
        //mainView.mapView.setRegion(MKCoordinateRegion(center: quebecCityCoordinate, span: startSpan), animated: false)
        //mainView.mapView.addAnnotation(PartAnnotation(coordinate: quebecCityCoordinate, iconName: "part-clutch"))
        
        _ = partService.partsObservable.register { (_, parts) in
            
            // Attempt to plot each landmark
            for landmark in parts {
                
                let name = landmark.name
                let component = landmark.component
                let imageName = "part-" + landmark.type
                
                // It JSON provided lat/lon, plot the landmark using that directly
                if let lat = landmark.latitude, let lon = landmark.longitude {
                    
                    self.plotPart(latitude: lat, longitude: lon, name: name,
                                  component: component, iconName: imageName)
                }
                    
                    // Otherwise we aim to extract the address and use Geolocation to determine lat/lon
                else {
                    
                    if let address = landmark.address {
                        
                        // Create Geolocation search request for address
                        let request = MKLocalSearchRequest()
                        request.naturalLanguageQuery = address
                        
                        // Formulate and execute MKLocalSearch
                        let search = MKLocalSearch(request: request)
                        search.start(completionHandler: { (searchResponse, error) in
                            
                            // Handle potential search error
                            if let error = error {
                                print("Error: Search address error\n\(error.localizedDescription)")
                                return
                            }
                            
                            if let searchResponse = searchResponse {
                                
                                let mapItems = searchResponse.mapItems
                                
                                // Pass only the first found marker for address, to be plotted on MapView
                                if let firstItemCoord = mapItems.first?.placemark.coordinate {
                                    
                                    // Do the UI drawing on the main thread
                                    DispatchQueue.main.async {
                                        self.plotLocationCoordinate(coordinate: firstItemCoord,
                                                                    name: name, component: component,
                                                                    iconName: imageName)
                                    }
                                }
                            }
                        })
                    }
                }
            }
        }
        
        /*
        guard let jsonUrl = URL(string: "https://s3.amazonaws.com/shared.ws.mirego.com/competition/mapping.json") else { return }
        
        // Create and initiate a data task to download the JSON from URL
        URLSession.shared.dataTask(with: jsonUrl) { (data, response, error) in
            guard let data = data else { return }
            
            do {
                
                // Decode the downloaded JSON data into Part object
                let decoder = JSONDecoder()
                let data = try decoder.decode([Part].self, from: data)

                
                // Attempt to plot each landmark
                for landmark in data {
                    
                    let name = landmark.name
                    let component = landmark.component
                    let imageName = "part-" + landmark.type
                    
                    // It JSON provided lat/lon, plot the landmark using that directly
                    if let lat = landmark.latitude, let lon = landmark.longitude {
                    
                        self.plotPart(latitude: lat, longitude: lon, name: name,
                                      component: component, iconName: imageName)
                    }
                    
                    // Otherwise we aim to extract the address and use Geolocation to determine lat/lon
                    else {
                        
                        if let address = landmark.address {
                            
                            // Create Geolocation search request for address
                            let request = MKLocalSearchRequest()
                            request.naturalLanguageQuery = address

                            // Formulate and execute MKLocalSearch
                            let search = MKLocalSearch(request: request)
                            search.start(completionHandler: { (searchResponse, error) in
                                
                                // Handle potential search error
                                if let error = error {
                                    print("Error: Search address error\n\(error.localizedDescription)")
                                    return
                                }
                                
                                if let searchResponse = searchResponse {
                                    
                                    let mapItems = searchResponse.mapItems
                                    
                                    // Pass only the first found marker for address, to be plotted on MapView
                                    if let firstItemCoord = mapItems.first?.placemark.coordinate {
                                        
                                        // Do the UI drawing on the main thread
                                        DispatchQueue.main.async {
                                            self.plotLocationCoordinate(coordinate: firstItemCoord,
                                                                        name: name, component: component,
                                                                        iconName: imageName)
                                        }
                                    }
                                }
                            })
                        }
                    }
                }
                
            } catch let err { print("Error: Unable to decode JSON into Part object", err) }
        }.resume()
 
    */
    }
    
    // Plot a part at a given latitude and longitude
    fileprivate func plotPart(latitude: Float, longitude: Float, name: String, component: String, iconName: String) {
        
        // Create the CLLocationCoordinate2D representation of the lat/lon
        let cLat = CLLocationDegrees(latitude)
        let cLon = CLLocationDegrees(longitude)
        let coords = CLLocationCoordinate2D(latitude: cLat, longitude: cLon)
        
        plotLocationCoordinate(coordinate: coords, name: name, component: component, iconName: iconName)
    }
    
    // Plot a CLLocationCoordinate2D object directly, into the mainView's mapView
    fileprivate func plotLocationCoordinate(coordinate: CLLocationCoordinate2D, name: String, component: String, iconName: String) {
        
        // Insert the map marker
        //self.mainView.mapView.setRegion(MKCoordinateRegion(center: coordinate, span: self.startSpan), animated: false)
        
        // Instantiate custom annotation
        let annotation = PartAnnotation(coordinate: coordinate, iconName: iconName, title: name, subtitle: component)
        
        //annotation.partName = iconName
        self.mainView.mapView.addAnnotation(annotation)
    }
}
