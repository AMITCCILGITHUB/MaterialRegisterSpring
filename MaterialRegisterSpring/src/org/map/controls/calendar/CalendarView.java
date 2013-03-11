package org.map.controls.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * A calendar control
 * 
 * @author Christian Schudt
 */
public class CalendarView extends VBox {

	private static final String CSS_CALENDAR = "calendar";

	// private static final String CSS_CALENDAR_TODAY_BUTTON =
	// "calendar-today-button";

	/**
	 * Initializes a calendar with the default locale.
	 */
	public CalendarView() {

		this(Locale.getDefault());
	}

	/**
	 * Initializes a calendar with the given locale. E.g. if the locale is
	 * en-US, the calendar starts the days on Sunday. If it is de-DE the
	 * calendar starts the days on Monday.
	 * <p/>
	 * Note that the Java implementation only knows
	 * {@link java.util.GregorianCalendar}
	 * 
	 * @param locale
	 *            The locale.
	 */
	public CalendarView(final Locale locale) {

		this(locale, Calendar.getInstance(locale));

		// When the locale changes, also change the calendar.
		this.locale.addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable observable) {

				calendar.set(Calendar.getInstance(localeProperty().get()));
			}
		});
	}

	/**
	 * Initializes the control with the given locale and the given calendar.
	 * <p/>
	 * This way, you can pass a custom calendar (e.g. you could implement the
	 * Hijri Calendar for the arabic world). Or you can use an American style
	 * calendar (starting with Sunday as first day of the week) together with
	 * another language.
	 * <p/>
	 * The locale determines the date format.
	 * 
	 * @param locale
	 *            The locale.
	 * @param calendar
	 *            The calendar
	 */
	public CalendarView(final Locale locale, final Calendar calendar) {

		this.locale.set(locale);
		this.calendar.set(calendar);

		getStyleClass().add(CSS_CALENDAR);

		setMaxWidth(Control.USE_PREF_SIZE);

		currentlyViewing.set(Calendar.MONTH);

		calendarDate.addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable observable) {

				calendar.setTime(calendarDate.get());
			}
		});
		this.calendarDate.set(new Date());
		currentDate.addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable observable) {

				Date date = new Date();
				if (currentDate.get() != null) {
					date = currentDate.get();
				}
				calendarDate.set(date);
			}
		});
		MainStackPane mainStackPane = new MainStackPane(this);
		VBox.setVgrow(mainStackPane, Priority.ALWAYS);
		mainNavigationPane = new MainNavigationPane(this);

		getChildren().addAll(mainNavigationPane, mainStackPane);
	}

	/**
	 * Gets or sets the locale.
	 * 
	 * @return The property.
	 */
	public ObjectProperty<Locale> localeProperty() {

		return locale;
	}

	private ObjectProperty<Locale> locale = new SimpleObjectProperty<>();

	public Locale getLocale() {

		return locale.get();
	}

	public void setLocale(Locale locale) {

		this.locale.set(locale);
	}

	/**
	 * Gets or sets the calendar.
	 * 
	 * @return The property.
	 */
	public ObjectProperty<Calendar> calendarProperty() {

		return calendar;
	}

	private ObjectProperty<Calendar> calendar = new SimpleObjectProperty<>();

	public Calendar getCalendar() {

		return calendar.get();
	}

	public void setCalendar(Calendar calendar) {

		this.calendar.set(calendar);
	}

	/**
	 * Gets the list of disabled week days. E.g. if you add
	 * <code>Calendar.WEDNESDAY</code>, Wednesday will be disabled.
	 * 
	 * @return The list.
	 */
	public ObservableList<Integer> getDisabledWeekdays() {

		return disabledWeekdays;
	}

	private ObservableList<Integer> disabledWeekdays = FXCollections
			.observableArrayList();

	/**
	 * Gets the list of disabled dates. You can add specific date, in order to
	 * disable them.
	 * 
	 * @return The list.
	 */
	public ObservableList<Date> getDisabledDates() {

		return disabledDates;
	}

	private ObservableList<Date> disabledDates = FXCollections
			.observableArrayList();

	/**
	 * Gets the selected date.
	 * 
	 * @return The property.
	 */
	public ReadOnlyObjectProperty<Date> selectedDateProperty() {

		return selectedDate;
	}

	private ObjectProperty<Date> currentDate = new SimpleObjectProperty<>();

	public ObjectProperty<Date> currentDateProperty() {

		return currentDate;
	}

	/**
	 * Indicates, whether the week numbers are shown.
	 * 
	 * @return The property.
	 */
	public BooleanProperty showWeeksProperty() {

		return showWeeks;
	}

	private BooleanProperty showWeeks = new SimpleBooleanProperty(false);

	public boolean getShowWeeks() {

		return showWeeks.get();
	}

	public void setShowWeeks(boolean showWeeks) {

		this.showWeeks.set(showWeeks);
	}

	/**
	 * Package internal properties.
	 */
	MainNavigationPane mainNavigationPane;
	/**
	 * Counts the current transitions. As long as an animation is going, the
	 * panels should not move left and right.
	 */
	IntegerProperty ongoingTransitions = new SimpleIntegerProperty(0);
	ObjectProperty<Date> selectedDate = new SimpleObjectProperty<>();
	ObjectProperty<Date> calendarDate = new SimpleObjectProperty<>();
	IntegerProperty currentlyViewing = new SimpleIntegerProperty();
	StringProperty title = new SimpleStringProperty();
}
