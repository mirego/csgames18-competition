//
//  NavigationViewController.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class NavigationViewController: UINavigationController, NavigationIconProvider {
    var navigationIcon: UIImage? {
        get {
            return (viewControllers.first as? BaseViewController)?.navigationIcon
        }
    }
}
