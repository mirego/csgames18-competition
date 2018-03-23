//
//  Stylesheet.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit

class Stylesheet {
    static func appearance() {
        UINavigationBar.appearance().barTintColor = .cooper
        UINavigationBar.appearance().isTranslucent = false
        UINavigationBar.appearance().titleTextAttributes = [NSAttributedStringKey.font: UIFont(name: "LeagueSpartan-Bold", size: 17)!, NSAttributedStringKey.foregroundColor: UIColor.white]
    }
}

extension UIColor {
    class var primary: UIColor { return UIColor(hex: 0xF7D239)! }
    class var secondary: UIColor { return UIColor(hex: 0xC5C4C3)! }
    class var cooper: UIColor { return UIColor(hex: 0xd1582a)! }
}
