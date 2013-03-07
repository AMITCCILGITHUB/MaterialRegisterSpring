package org.map.utils;

import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ContextMenuBuilder;
import javafx.scene.control.MenuItemBuilder;

public class TableContextMenu {

	public static ContextMenu getViewUserContextMenu(
			EventHandler<ActionEvent> eventHandler1,
			EventHandler<ActionEvent> eventHandler2)
			throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("Show Password")
						.onAction(eventHandler1)
						.graphic(FileUtil.getImageAsImageView("search"))
						.build(),
						MenuItemBuilder
								.create()
								.text("View User")
								.onAction(eventHandler2)
								.graphic(
										FileUtil.getImageAsImageView("view_user"))
								.build()).build();
	}

	public static ContextMenu getEditUserContextMenu(
			EventHandler<ActionEvent> eventHandler1,
			EventHandler<ActionEvent> eventHandler2)
			throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder
						.create()
						.text("Edit User")
						.onAction(eventHandler1)
						.graphic(
								FileUtil.getImageAsImageView("edit-clear",
										".gif")).build(),
						MenuItemBuilder
								.create()
								.text("Delete User")
								.onAction(eventHandler2)
								.graphic(FileUtil.getImageAsImageView("delete"))
								.build()).build();
	}

	public static ContextMenu getPrintMaterialContextMenu(
			EventHandler<ActionEvent> eventHandler)
			throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("Print")
						.onAction(eventHandler)
						.graphic(FileUtil.getImageAsImageView("print")).build())
				.build();
	}

	public static ContextMenu getEditMaterialContextMenu(
			EventHandler<ActionEvent> eventHandler1,
			EventHandler<ActionEvent> eventHandler2)
			throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder
						.create()
						.text("Edit Material")
						.onAction(eventHandler1)
						.graphic(
								FileUtil.getImageAsImageView("edit-clear",
										".gif")).build(),
						MenuItemBuilder
								.create()
								.text("Delete Material")
								.onAction(eventHandler2)
								.graphic(FileUtil.getImageAsImageView("delete"))
								.build()).build();
	}

	public static ContextMenu getAddMaterialContextMenu(
			EventHandler<ActionEvent> eventHandler1,
			EventHandler<ActionEvent> eventHandler2,
			EventHandler<ActionEvent> eventHandler3)
			throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("Add Test")
						.onAction(eventHandler1)
						.graphic(FileUtil.getImageAsImageView("context_add"))
						.build(),
						MenuItemBuilder
								.create()
								.text("Remove Test")
								.onAction(eventHandler2)
								.graphic(
										FileUtil.getImageAsImageView("context_delete"))
								.build(),
						MenuItemBuilder
								.create()
								.text("Duplicate Test")
								.onAction(eventHandler3)
								.graphic(
										FileUtil.getImageAsImageView("lightbulb"))
								.build()).build();
	}

	public static ContextMenu getViewMaterialContextMenu(
			EventHandler<ActionEvent> eventHandler)
			throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("View Material")
						.onAction(eventHandler)
						.graphic(FileUtil.getImageAsImageView("lightbulb"))
						.build()).build();
	}

	public static ContextMenu getAddHeatChartContextMenu(
			EventHandler<ActionEvent> eventHandler1,
			EventHandler<ActionEvent> eventHandler2,
			EventHandler<ActionEvent> eventHandler3)
			throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("Add Record")
						.onAction(eventHandler1)
						.graphic(FileUtil.getImageAsImageView("context_add"))
						.build(),
						MenuItemBuilder
								.create()
								.text("Add Sheet")
								.onAction(eventHandler2)
								.graphic(
										FileUtil.getImageAsImageView("context_add"))
								.build(),
						MenuItemBuilder
								.create()
								.text("Remove Record")
								.onAction(eventHandler3)
								.graphic(
										FileUtil.getImageAsImageView("context_delete"))
								.build()).build();
	}

	public static ContextMenu getViewHeatChartContextMenu(
			EventHandler<ActionEvent> eventHandler)
			throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder.create().text("View Heat Chart")
						.onAction(eventHandler)
						.graphic(FileUtil.getImageAsImageView("lightbulb"))
						.build()).build();
	}

	public static ContextMenu getEditHeatChartContextMenu(
			EventHandler<ActionEvent> eventHandler1,
			EventHandler<ActionEvent> eventHandler2)
			throws MalformedURLException {

		return ContextMenuBuilder
				.create()
				.items(MenuItemBuilder
						.create()
						.text("Edit Heat Chart")
						.onAction(eventHandler1)
						.graphic(
								FileUtil.getImageAsImageView("edit-clear",
										".gif")).build(),
						MenuItemBuilder
								.create()
								.text("Delete Heat Chart")
								.onAction(eventHandler2)
								.graphic(FileUtil.getImageAsImageView("delete"))
								.build()).build();
	}
}
