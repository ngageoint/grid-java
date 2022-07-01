package mil.nga.grid.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Grid property loader
 *
 * @author osbornb
 */
public abstract class GridProperties {

	/**
	 * Logger
	 */
	private static final Logger log = Logger
			.getLogger(GridProperties.class.getName());

	/**
	 * Properties
	 */
	protected Properties mProperties;

	/**
	 * Get the properties file name
	 * 
	 * @return file name
	 */
	public abstract String getFile();

	/**
	 * Get a required property by key
	 *
	 * @param key
	 *            key
	 * @return value
	 */
	public String getProperty(String key) {
		return getProperty(key, true);
	}

	/**
	 * Get a property by key
	 *
	 * @param key
	 *            key
	 * @param required
	 *            true if required
	 * @return value
	 */
	public synchronized String getProperty(String key, boolean required) {
		if (mProperties == null) {
			mProperties = initializeConfigurationProperties();
		}
		String value = mProperties.getProperty(key);
		if (value != null && value.trim().isEmpty()) {
			value = null;
		}
		if (value == null && required) {
			throw new IllegalArgumentException("Property not found: " + key);
		}
		return value;
	}

	/**
	 * Get a required property by property parts
	 *
	 * @param properties
	 *            property parts
	 * @return value
	 */
	public String getProperty(String... properties) {
		return getProperty(true, properties);
	}

	/**
	 * Get a property by property parts
	 *
	 * @param required
	 *            true if required
	 * @param properties
	 *            property parts
	 * @return value
	 */
	public String getProperty(boolean required, String... properties) {
		return getProperty(buildProperty(properties), required);
	}

	/**
	 * Get a required integer property by key
	 *
	 * @param key
	 *            key
	 * @return integer value
	 */
	public int getIntegerProperty(String key) {
		return getIntegerProperty(key, true);
	}

	/**
	 * Get an integer property by key
	 *
	 * @param key
	 *            key
	 * @param required
	 *            true if required
	 * @return integer value
	 */
	public Integer getIntegerProperty(String key, boolean required) {
		Integer value = null;
		String stringValue = getProperty(key, required);
		if (stringValue != null) {
			value = Integer.valueOf(stringValue);
		}
		return value;
	}

	/**
	 * Get a required integer property by property parts
	 *
	 * @param properties
	 *            property parts
	 * @return integer value
	 */
	public int getIntegerProperty(String... properties) {
		return getIntegerProperty(true, properties);
	}

	/**
	 * Get an integer property by property parts
	 *
	 * @param required
	 *            true if required
	 * @param properties
	 *            property parts
	 * @return integer value
	 */
	public Integer getIntegerProperty(boolean required, String... properties) {
		return getIntegerProperty(buildProperty(properties), required);
	}

	/**
	 * Get a required float by key
	 *
	 * @param key
	 *            key
	 * @return float value
	 */
	public float getFloatProperty(String key) {
		return getFloatProperty(key, true);
	}

	/**
	 * Get a float by key
	 *
	 * @param key
	 *            key
	 * @param required
	 *            true if required
	 * @return float value
	 */
	public Float getFloatProperty(String key, boolean required) {
		Float value = null;
		String stringValue = getProperty(key, required);
		if (stringValue != null) {
			value = Float.valueOf(stringValue);
		}
		return value;
	}

	/**
	 * Get a required float property by property parts
	 *
	 * @param properties
	 *            property parts
	 * @return float value
	 */
	public float getFloatProperty(String... properties) {
		return getFloatProperty(true, properties);
	}

	/**
	 * Get a float property by property parts
	 *
	 * @param required
	 *            true if required
	 * @param properties
	 *            property parts
	 * @return float value
	 */
	public Float getFloatProperty(boolean required, String... properties) {
		return getFloatProperty(buildProperty(properties), required);
	}

	/**
	 * Get a required double by key
	 *
	 * @param key
	 *            key
	 * @return double value
	 */
	public double getDoubleProperty(String key) {
		return getDoubleProperty(key, true);
	}

	/**
	 * Get a double by key
	 *
	 * @param key
	 *            key
	 * @param required
	 *            true if required
	 * @return double value
	 */
	public Double getDoubleProperty(String key, boolean required) {
		Double value = null;
		String stringValue = getProperty(key, required);
		if (stringValue != null) {
			value = Double.valueOf(stringValue);
		}
		return value;
	}

	/**
	 * Get a required double property by property parts
	 *
	 * @param properties
	 *            property parts
	 * @return double value
	 */
	public double getDoubleProperty(String... properties) {
		return getDoubleProperty(true, properties);
	}

	/**
	 * Get a double property by property parts
	 *
	 * @param required
	 *            true if required
	 * @param properties
	 *            property parts
	 * @return double value
	 */
	public Double getDoubleProperty(boolean required, String... properties) {
		return getDoubleProperty(buildProperty(properties), required);
	}

	/**
	 * Get a required boolean by key
	 *
	 * @param key
	 *            key
	 * @return boolean value
	 */
	public boolean getBooleanProperty(String key) {
		return getBooleanProperty(key, true);
	}

	/**
	 * Get a boolean by key
	 *
	 * @param key
	 *            key
	 * @param required
	 *            true if required
	 * @return boolean value
	 */
	public Boolean getBooleanProperty(String key, boolean required) {
		Boolean value = null;
		String stringValue = getProperty(key, required);
		if (stringValue != null) {
			value = Boolean.valueOf(stringValue);
		}
		return value;
	}

	/**
	 * Get a required boolean property by property parts
	 *
	 * @param properties
	 *            property parts
	 * @return boolean value
	 */
	public boolean getBooleanProperty(String... properties) {
		return getBooleanProperty(true, properties);
	}

	/**
	 * Get a boolean property by property parts
	 *
	 * @param required
	 *            true if required
	 * @param properties
	 *            property parts
	 * @return boolean value
	 */
	public Boolean getBooleanProperty(boolean required, String... properties) {
		return getBooleanProperty(buildProperty(properties), required);
	}

	/**
	 * Initialize the configuration properties
	 *
	 * @return properties
	 */
	private Properties initializeConfigurationProperties() {
		Properties properties = new Properties();

		String file = getFile();

		InputStream in = GridProperties.class.getResourceAsStream("/" + file);
		if (in != null) {
			try {
				properties.load(in);
			} catch (Exception e) {
				log.log(Level.SEVERE, "Failed to load properties file: " + file,
						e);
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					log.log(Level.WARNING,
							"Failed to close properties file: " + file, e);
				}
			}
		} else {
			log.log(Level.SEVERE,
					"Failed to load properties, file not found: " + file);
		}

		return properties;
	}

	/**
	 * Build a combined property separated by
	 * {@link PropertyConstants#PROPERTY_DIVIDER}
	 *
	 * @param properties
	 *            property parts
	 *
	 * @return combined property
	 */
	public String buildProperty(String... properties) {
		StringBuilder combined = new StringBuilder();
		for (String property : properties) {
			if (property != null) {
				if (combined.length() > 0) {
					combined.append(PropertyConstants.PROPERTY_DIVIDER);
				}
				combined.append(property);
			}
		}
		return combined.toString();
	}

}
