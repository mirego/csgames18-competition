//
//  BaseViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class BaseViewController: UIViewController, NavigationIconProvider {
    var viewControllerFactory: ViewControllerFactory!
    var navigationIcon: UIImage?
}
