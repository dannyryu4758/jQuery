package kr.or.ddit.reflection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.sound.sampled.ReverbType;

import kr.or.ddit.reflect.ReflectionTest;

public class ReflectionDesc {
	public static void main(String[] args) {
		// marshalling : java, c, javascript 언어 -> xml, json 언어로 변환하는 작업 (반대의 작업 : unMarshalling)
		Test t = new Test();
		t.setNumber(42);
		t.setStr("text");
//		{"number" : 42, "str" : "text"} // json 데이터
		String propPattern = "\"%s\":%s,";
		StringBuffer json = new StringBuffer();
		try {
			json.append("{");
			Class<Test>  tType = Test.class;
			Field[] fields = tType.getDeclaredFields();
			for(Field tmp:fields) {
				String name = tmp.getName();
				tmp.setAccessible(true); // private 변수를 public 으로 변경한다.
				Object retVal;
					retVal = tmp.get(t);
				Class<?> fidType =  tmp.getType(); // 변수 타입 가져오기
				String formatValue = null;
				if(retVal!=null && fidType.equals(String.class)) {
					// 문자열 타입의 전역변수 일때 여기로 들어온다.
					formatValue = "\"" + retVal + "\"";
				} else {
					formatValue = Objects.toString(retVal);
				}
				json.append(String.format(propPattern, name, formatValue));
				
			} // for end
			if(json.lastIndexOf(",") == json.length()-1)
					json.deleteCharAt(json.lastIndexOf(","));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		json.append("}");
		System.out.println(json);
		
		
	}
	
	public static void refectionTest(){
		Test t = new Test();
		t.setNumber(42);
		t.setStr("text");
		System.out.println(t.merge());
		
		Object instance = ReflectionTest.getObject();
		Class<?> instanceType = instance.getClass(); // Class : 클래스를 대표하는 클래스
		System.out.println(instanceType.getName());
		// 모든 전역변수의 정보 저장 (public 변수만 가져온다)
//		Field[] fields = instanceType.getFields();
		// 모든 전역변수의 정보 저장
		Field[] fields = instanceType.getDeclaredFields();
		for(Field fid : fields) {
			// 변수명 받기
			String name = fid.getName();
			Class<?> fidType = fid.getType();
			System.out.printf("%s %s;\n", fidType.getSimpleName(), name);
			try {
				// PropertyDescriptor : 프로퍼티 하나에 대한 정보를 가지고 있음. (프로퍼티는 자바빈 규약에 의해 만들어진 변수를 프로퍼티 라 한다)
				PropertyDescriptor pd = new PropertyDescriptor(name, instanceType);
				// getter 메서드 호출하기
				Object retVal = pd.getReadMethod().invoke(instance);
				System.err.printf("%s 호출반환 : %s\n", pd.getReadMethod().getName(), retVal);
			} catch (IntrospectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			String getter = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
			try {
//				Method method = instanceType.getMethod(getter);	// public 메소드만 가져온다.
				Method method = instanceType.getDeclaredMethod(getter); // 모든 메소드를 가져온다.
//				member.getMem_id();
				Object retValue = method.invoke(instance);	// (주인객체,파라미터) - 파라미터는 필요없으면 빼도 된다.
				System.out.printf("%s 호출반환 : %s\n", method.getName(), retValue);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {	// private 메소드 일때
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {	// 파라미터가 있어야 하는데 안들어왔을때
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {	// 관계 없는 객체를 주인객체로 넣었을때
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static class Test{
		private String str;	// 상태 
		private Integer number; // 상태
		
		public String merge(){	// 행동
			return str + number;
		}

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}
		
		
	}
}
