//
//  ListViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class ListViewController: BaseViewController, ContentSizeUpdate {

    //NR: USELESS?!?!
    /*private var mainView: ListView {
        return self.view as! ListView
    }*/

    private let partService: PartService
    private let scroll: UIScrollView

    init(partService: PartService) {
        self.partService = partService
        self.scroll = UIScrollView();
        super.init(nibName: nil, bundle: nil)
        title = "Map Ping"
        navigationIcon = #imageLiteral(resourceName: "icn-list")
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
    

    override func loadView() {
        view = scroll
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        let listView = ListView()
        listView.contentSizeDelegate = self
        scroll.addSubview(listView)
        
        _ = partService.partsObservable.register { (_, parts) in
            print("Parts received!!")
            print("Nb of parts received: \(parts.count)")
            //Add the parts to the list
        }
    }
    
    override func viewDidLayoutSubviews() {
        scroll.backgroundColor = .white
        scroll.isUserInteractionEnabled = true
        //scroll.contentSize = CGSize(width: self.view.frame.size.width, height: 5000);
    }
    
    //MARK: ContentSizeUpdate
    func updateContentSize(height: CGFloat) {
        scroll.contentSize = CGSize(width: self.view.frame.size.width, height: height)
    }
}
