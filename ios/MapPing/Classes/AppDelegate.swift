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

    private let serviceFactory = ServiceFactory()
    private let viewControllerFactory: ViewControllerFactory

    override init() {
        viewControllerFactory = ViewControllerFactory(serviceFactory: serviceFactory)
        super.init()
    }
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        Stylesheet.appearance()

        window = UIWindow(frame: UIScreen.main.bounds)
        window!.backgroundColor = .white
        window!.tintColor = .primary
        window!.rootViewController = viewControllerFactory.rootViewController()
        window!.makeKeyAndVisible()

        return true
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        serviceFactory.partService().refreshParts()
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        serviceFactory.partService().refreshParts()
    }
}
