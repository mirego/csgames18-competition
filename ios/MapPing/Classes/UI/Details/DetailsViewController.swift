//
//  DetailsViewController.swift
//  MapPing
//
//  Created by Quentin Nolan on 18-03-24.
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class DetailsViewController: BaseViewController {
    
    private var mainView: DetailsView {
        return self.view as! DetailsView
    }
    
    private let part: Part

    init(part: Part) {
        self.part = part
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    
    override func loadView() {
        view = DetailsView()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.mainView.configure(part: part)
    }
    
}
