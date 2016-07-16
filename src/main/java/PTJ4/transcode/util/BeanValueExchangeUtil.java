package PTJ4.transcode.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author vwings
 * 交换bean属性
 *
 */
public class BeanValueExchangeUtil {

	/**
	 * @param obj1 接受属性对象
	 * @param obj2 提供属性对象
	 */
	public static <T> void exchangeBeanValue(T obj1,T obj2)
	{
		Class class1 = obj1.getClass();
		Class class2 = obj2.getClass();
		
		if(!class1.getName().equals(class2.getName()))
		{
			return ;
		}
		
		Method[] methods = class1.getDeclaredMethods();
		Map<String,Method> getters = new HashMap<String, Method>();
		Map<String,Method> setters = new HashMap<String, Method>();
		
		for(Method method : methods)
		{
			int subNum = 0;
			boolean isGetter = false;
			boolean isSetter = false;
			if(method.getName().substring(0, 3).equals("get") && !method.getName().equals("getClass"))
			{
				subNum = 3;
				isGetter = true;
			}
			if(method.getName().substring(0, 2).equals("is"))
			{
				subNum = 2;
				isGetter = true;
			}
			if(method.getName().substring(0, 3).equals("set"))
			{
				isSetter = true;
			}
			
			if(isGetter)
			{
				//�洢�ֶ����ͷ�����
				getters.put(method.getName().substring(subNum), method);
			}
			if(isSetter)
			{
				setters.put(method.getName().substring(3), method);
			}
		}
		

		for(Entry<String, Method> entry : setters.entrySet())
		{
			Method setter = entry.getValue();
			Method getter = getters.get(entry.getKey());
			try {
				Object result = getter.invoke(obj2);
				//新对象的空值不要进行赋值
				if(result != null)
					setter.invoke(obj1, result);
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		
	}
}
