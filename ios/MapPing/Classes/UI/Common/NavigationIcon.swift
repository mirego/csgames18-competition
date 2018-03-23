//
//  NavigationIcon.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

protocol NavigationIconProvider where Self : UIViewController {
    var navigationIcon: UIImage? { get }
}
