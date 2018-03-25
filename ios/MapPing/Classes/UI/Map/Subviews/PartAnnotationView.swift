//
//  PartAnnotationView.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import MapKit

class PartAnnotationView: MKAnnotationView {
    static let reuseIdentifier = "PartAnnotationView"

    private let pinImage = UIImageView(image: #imageLiteral(resourceName: "icn-pin"))
    private let partCallout = UIView()
    private let partName = UILabel()
    private let partImage = UIImageView()
    private let calloutButton = UIButton(type: .detailDisclosure)
    
    private let calloutDetailsPressed: (() -> ())? = nil

    override init(annotation: MKAnnotation?, reuseIdentifier: String?) {
        super.init(annotation: annotation, reuseIdentifier: reuseIdentifier)

        addSubview(pinImage)
        addSubview(partCallout)
        addSubview(partImage)
        partCallout.addSubview(partName)
        partCallout.addSubview(calloutButton)
        calloutButton.addTarget(self, action: #selector(calloutDetailPressed), for: .touchUpInside)
        size = pinImage.size
        partCallout.isHidden = true
    }
    
    @objc
    func calloutDetailPressed() {
        calloutDetailsPressed?()
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        partImage.pin.hCenter().top(11).size(CGSize(width: 22, height: 22))
        partCallout.pin.hCenter().top(-64).size(CGSize(width: 128, height: 48))
        partCallout.setRoundCornersMask()
        partCallout.backgroundColor = UIColor(hex: 0x000000, alpha: 0.7)
        partName.pin.vCenter().width(100).sizeToFit(.widthFlexible).maxHeight(32).before(of: calloutButton)
        partName.textColor = UIColor.white
        calloutButton.pin.right(8).vCenter()
    }

    func configure(partAnnotation: PartAnnotation, onDetailPressed: (() -> ())? = nil) {
        partImage.image = UIImage(named: partAnnotation.iconName)
        partName.text = partAnnotation.name
        setNeedsLayout()
    }
    
    func didSelect() {
        partCallout.isHidden = false
    }
    
    func didDeselect() {
        partCallout.isHidden = true
    }
    
    // Click
    
    override func hitTest(_ point: CGPoint, with event: UIEvent?) -> UIView? {
        let hitView = super.hitTest(point, with: event)
        if (hitView != nil) {
            self.superview?.bringSubview(toFront: self)
        }
        return hitView;
    }
    
    override func point(inside point: CGPoint, with event: UIEvent?) -> Bool {
        let rect = self.bounds
        var isInside = rect.contains(point)
        
        if(!isInside) {
            for view in self.subviews {
                isInside = view.frame.contains(point)
                break;
            }
        }
        
        return isInside
    }
 
}
