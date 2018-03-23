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

        mapView.register(PieceAnnotationView.self, forAnnotationViewWithReuseIdentifier: PieceAnnotationView.reuseIdentifier)
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
        guard !(annotation is MKUserLocation), let annotation = annotation as? PieceAnnotation else { return nil }

        let annotationView = mapView.dequeueReusableAnnotationView(withIdentifier: PieceAnnotationView.reuseIdentifier, for: annotation) as! PieceAnnotationView
        annotationView.configure(pieceImageName: annotation.iconName)
        return annotationView
    }
}
