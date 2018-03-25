//
//  MapView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import MapKit

class MapView: UIView {
    let mapView = MKMapView()

    init() {
        super.init(frame: .zero)

        mapView.register(PartAnnotationView.self, forAnnotationViewWithReuseIdentifier: PartAnnotationView.reuseIdentifier)
        mapView.delegate = self
        addSubview(mapView)
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        mapView.pin.all()
    }
}

extension MapView: MKMapViewDelegate {
 
     func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        guard !(annotation is MKUserLocation), let annotation = annotation as? PartAnnotation else { return nil }
     
        let annotationView = mapView.dequeueReusableAnnotationView(withIdentifier: PartAnnotationView.reuseIdentifier, for: annotation) as! PartAnnotationView
        annotationView.configure(partImageName: annotation.iconName)
        return annotationView
     }
 
    
    
    
    // Fires when an MKAnnotationView is selected
    func mapView(_ mapView: MKMapView, didSelect view: MKAnnotationView) {
        
    }
}
