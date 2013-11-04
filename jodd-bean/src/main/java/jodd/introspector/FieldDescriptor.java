// Copyright (c) 2003-2013, Jodd Team (jodd.org). All Rights Reserved.

package jodd.introspector;

import jodd.util.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Field descriptor. Holds additional field data,
 * that might be specific to implementation class.
 */
public class FieldDescriptor extends Descriptor {

	protected final Field field;
	protected final Type type;
	protected final Class rawType;
	protected final Class rawComponentType;
	protected final Class rawKeyComponentType;

	/**
	 * Creates new field descriptor and resolve all additional field data.
	 * Also, forces access to a field.
	 */
	public FieldDescriptor(ClassDescriptor classDescriptor, Field field) {
		super(classDescriptor, ReflectUtil.isPublic(field));
		this.field = field;
		this.type = field.getGenericType();
		this.rawType = ReflectUtil.getRawType(type, classDescriptor.getType());
		this.rawComponentType = ReflectUtil.getComponentType(type, classDescriptor.getType());
		this.rawKeyComponentType = ReflectUtil.getComponentType(type, classDescriptor.getType(), 0);

		ReflectUtil.forceAccess(field);
	}

	/**
	 * Returns field.
	 */
	public Field getField() {
		return field;
	}

	/**
	 * Returns fields raw type.
	 */
	public Class getRawType() {
		return rawType;
	}

	/**
	 * Returns fields raw component type. Returns <code>null</code>
	 * if field has no component type.
	 */
	public Class getRawComponentType() {
		return rawComponentType;
	}

	/**
	 * Returns fields raw component type. Returns <code>null</code>
	 * if field has no component type.
	 */
	public Class getRawKeyComponentType() {
		return rawKeyComponentType;
	}

	/**
	 * Resolves raw component type for given index. This value is NOT cached.
	 */
	public Class resolveRawComponentType(int index) {
		return ReflectUtil.getComponentType(type, classDescriptor.getType(), index);
	}

}