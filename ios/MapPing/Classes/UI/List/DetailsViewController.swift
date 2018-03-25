//
//  DetailsViewController.swift
//  MapPing
//
//  Created by Joseph Roque on 2018-03-24.
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class DetailsViewController: BaseViewController {
    
    private let part: Part
    
    init(part: Part) {
        self.part = part
        super.init(nibName: nil, bundle: nil)
        title = part.name
        navigationIcon = #imageLiteral(resourceName: "icn-list")
    }
    
    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .white
        
        let notesTitle = UILabel()
        notesTitle.text = "Notes"
        notesTitle.font = notesTitle.font.withSize(24.0)
        
        let notes = UILabel()
        notes.text = part.notes

        view.addSubviewsForAutolayout(notesTitle, notes)
        
        notesTitle.pin.top().horizontally(UIEdgeInsets(top: 16.0, left: 16.0, bottom: 16.0, right: 16.0)).sizeToFit(.width)
        notes.pin.below(of: notesTitle).horizontally(UIEdgeInsets(top: 0, left: 16.0, bottom: 16.0, right: 16.0)).sizeToFit(.width)
    }
}

