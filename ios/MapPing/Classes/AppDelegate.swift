//
//  AppDelegate.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    private let viewControllerFactory = ViewControllerFactory()

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        window!.backgroundColor = .white
        window!.tintColor = .primary
        window!.rootViewController = viewControllerFactory.rootViewController()
        window!.makeKeyAndVisible()
        return true
    }
}
