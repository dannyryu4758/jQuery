package kr.or.ddit.board.controller;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;

/**
 * Marshalling : 특정언어로 표현된 데이터(java object) 를 범용 방식(xml/json)으로 변환하는 작업.
 * 
 */
public class MarshallingUtils {
	public String marshalling(Object target) {
		// 1. target이 marshalling 이 가능한 데이터인지를 확인 (값만 들어있는 요소는 marshalling 불가)
		// int(Integer), double(Double), char, String, StringBuffer
		// 그래서 검사해서 걸러주고 바꿔줘야 하는 작업을 해야 한다.

		if (ClassUtils.isPrimitiveWrapper(target.getClass()) || 
				// ==> marshaling 불가능한 것(Primitive Wrapper-(Boolean, Byte, Character, Short, Integer, Long, Double, Float).) 걸러내기
				CharSequence.class.isAssignableFrom(target.getClass())) { // AssignableFrom : 문자열의 부모인지를 확인
			throw new IllegalArgumentException("마샬링이 불가능한 데이터"); 
		}
		return marshallingObjectToJson(target);
		//*******************질문 : 여기서 if문 통해서 걸러서 마샬링 불가라고 오류 띄웠는데 어떻게 마샬링을 하나??
		// marshallingObjectToJson() 메소드 안에 같은 조건문이 있음에도 마샬링을 수행하는데 .,.... 왜???
		// 어떤게 마샬링이 안된다는건지 ???
	}

	private String marshallingObjectToJson(Object target) {
//		"c", 42, true, "str", {}, [], Map{}
		if (target == null)
			return null;
		Class<?> targetType = target.getClass();
		String value = null;
		if (CharSequence.class.isAssignableFrom(targetType)
				|| ClassUtils.isAssignable(targetType, Character.class, true)) { // 기본형 문자 타입 걸러내기
			value = String.format("\"%s\"", target);
		} else if (ClassUtils.isPrimitiveOrWrapper(targetType)) { // 문자를 제외한 나머지 타입 걸러내기
			value = target.toString();
		} else if (targetType.isArray()) { // 배열인지 확인
//			Object[] array = (Object[]) target;
			value = marshallingArrayToJson(target);
		} else if (target instanceof Map) {
			Map map = (Map) target;
			value = marshallingMapToJson(map);
		} else {
			StringBuffer json = new StringBuffer("{");
			Field[] fields = targetType.getDeclaredFields(); // 해당 클래스에서 정의된 변수 목록을 field 클래스 배열 타입으로 리턴한다.
			for (Field tmp : fields) {
				String name = tmp.getName();	// 변수명 가져오기
				try {
					PropertyDescriptor pd = new PropertyDescriptor(name, targetType);	//  변수(name)애 대한 getter, setter 정보를 를 가져온다. 
					Object propValue = pd.getReadMethod().invoke(target); // getReadMethod()를 통해 getter 메소드를 수행하여 저장되어 있는 변수값을 가져온다.
					json.append(String.format(PROPERTYPATTERN, name, marshallingObjectToJson(propValue))); // 가져온 변수값를 마샬링을 메소드 재귀호출로 마샬링한다. 
				} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					System.err.println(e.getMessage());
					continue;
				}
			}
			int lastIndex = json.lastIndexOf(",");
			if (lastIndex == json.length() - 1) {
				json.deleteCharAt(lastIndex);
			}
			value = json.append("}").toString();
		}
		return value;
	}

	private final String PROPERTYPATTERN = "\"%s\":%s,";

	private String marshallingMapToJson(Map map) {
		if (map == null)
			return null;
		Iterator keys = map.keySet().iterator();
		StringBuffer json = new StringBuffer("{");
		while (keys.hasNext()) {
			Object key = (Object) keys.next();
			Object value = map.get(key);
			json.append(String.format(PROPERTYPATTERN, key, marshallingObjectToJson(value)));
		}
		int lastIndex = json.lastIndexOf(",");
		if (lastIndex == json.length() - 1) {
			json.deleteCharAt(lastIndex);
		}
		json.append("}");
		return json.toString();
	}

//   private commaDeleter

	public String marshallingArrayToJson(Object array) {
		StringBuffer json = new StringBuffer("[");
		if (array != null) {
			int length = Array.getLength(array);
			for (int i=0 ; i<length; i++) {
				json.append(marshallingObjectToJson(Array.get(array, i)) + ",");
			}
			int lastIndex = json.lastIndexOf(",");
			if (lastIndex == json.length() - 1) {
				json.deleteCharAt(lastIndex);
			}
		}
		json.append("]");
		return json.toString();
	}

	public static class TestVO {
		private Integer number = new Integer(32);
		private int[] numbers = new int[] {1,2,3};
		private int num = 311;
		private String[] array = new String[] { "a", "b" };
		private Map<String, Object> map = new HashMap<>();

		{
			map.put("key1", number);
			map.put("key2", num);
			map.put("key3", array);
//         map.put("key4",new TestVO());
		}

		
		
		public int[] getNumbers() {
			return numbers;
		}

		public void setNumbers(int[] numbers) {
			this.numbers = numbers;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public String[] getArray() {
			return array;
		}

		public void setArray(String[] array) {
			this.array = array;
		}

		public Map<String, Object> getMap() {
			return map;
		}

		public void setMap(Map<String, Object> map) {
			this.map = map;
		}

	}

	public static void main(String[] args) {
//      Object target = "text";
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      target = 'c';
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      target = new Character('a');
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      target = new StringBuffer("text2");
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      target = 32;
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      target = true;
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      target = null;
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      target = new Integer[] {1,23,42,12};
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      target = new char[] {'a','b'};
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      target = new String[][] {{"d"},{"f"},{"12d","23v"}};
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      target = new String[] {};
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      Map<String, Object> map = new HashMap<String, Object>();
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      map.put("key1",32);
//      target = map;
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      map.put("key2","te");
//      target = map;
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      map.put("key3",new Character('c'));
//      target = map;
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      map.put("key3",new Integer[] {1,3});
//      target = map;
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));

		TestVO test = new TestVO();
		String result = new MarshallingUtils().marshalling(test);
		System.out.println(result);
	}
}