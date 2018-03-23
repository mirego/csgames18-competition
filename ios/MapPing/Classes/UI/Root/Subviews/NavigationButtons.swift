//
//  NavigationButtons.swift
//  MapPing
//
//  Copyright © 2018 Mirego. All rights reserved.
//

import UIKit
import MRGTaylor

protocol NavigationButtonsDelegate: class {
    func didTapButton(at index: Int)
}

class NavigationButtons: UIView {
    private static let buttonSize = CGSize(width: 45, height: 46)

    weak var delegate: NavigationButtonsDelegate?

    private let buttons: [UIButton]
    private let delimiters: [UIView]

    init(images: [UIImage]) {
        assert(!images.isEmpty, "NavigationButtons needs at least one images")

        buttons = images.map { NavigationButtons.buildButton(image: $0) }
        var views: [UIView] = []
        for _ in 1...(buttons.count - 1) {
            views.append(UIView())
        }
        delimiters = views
        super.init(frame: .zero)

        backgroundColor = UIColor.white.withAlphaComponent(0.95)
        setShadow(radius: 3, offset: CGSize(width: 0, height: 0), opacity: 0.25, color: .black)
        layer.cornerRadius = 10

        for (index, button) in buttons.enumerated() {
            addSubview(button)
            button.addAction(events: .touchUpInside, { [weak self] (_) in
                self?.delegate?.didTapButton(at: index)
            })
        }
        delimiters.forEach {
            $0.backgroundColor = .secondary
            addSubview($0)
        }
        pin.size(CGSize(width: CGFloat(buttons.count) * NavigationButtons.buttonSize.width, height: NavigationButtons.buttonSize.height))
    }

    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        layer.shadowPath = UIBezierPath(roundedRect: bounds, cornerRadius: layer.cornerRadius).cgPath
        for (index, button) in buttons.enumerated() {
            button.pin.centerLeft().marginLeft(CGFloat(index) * NavigationButtons.buttonSize.width)
            if index < delimiters.count {
                delimiters[index].pin.right(of: button).size(CGSize(width: 1, height: height))
            }
        }
    }

    func setSelectedButton(index: Int) {
        for (buttonIndex, button) in buttons.enumerated() {
            button.isEnabled = index != buttonIndex
        }
    }

    private static func buildButton(image: UIImage) -> UIButton {
        let button = UIButton()
        button.setImage(image.imageWithTintColor(.secondary), for: .normal)
        button.setImage(image.imageWithTintColor(.primary), for: .disabled)
        button.pin.size(buttonSize)
        return button
    }
}
