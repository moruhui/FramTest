package mrh.framtest20160801.configs.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yiku
 * @date 2016/7/18 9:39
 */
public class ListUtils {
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    public ListUtils() {
    }

    public static <V> int getSize(List<V> sourceList) {
        return sourceList == null?0:sourceList.size();
    }

    public static <V> boolean isEmpty(List<V> sourceList) {
        return sourceList == null || sourceList.size() == 0;
    }

    public static <V> boolean isEquals(ArrayList<V> actual, ArrayList<V> expected) {
        if(actual == null) {
            return expected == null;
        } else if(expected == null) {
            return false;
        } else if(actual.size() != expected.size()) {
            return false;
        } else {
            for(int i = 0; i < actual.size(); ++i) {
                if(!ObjectUtils.isEquals(actual.get(i), expected.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String join(List<String> list) {
        return join(list, ",");
    }

    public static String join(List<String> list, char separator) {
        return join(list, new String(new char[]{separator}));
    }

    public static String join(List<String> list, String separator) {
        if(isEmpty(list)) {
            return "";
        } else {
            if(separator == null) {
                separator = ",";
            }

            StringBuilder joinStr = new StringBuilder();

            for(int i = 0; i < list.size(); ++i) {
                joinStr.append((String)list.get(i));
                if(i != list.size() - 1) {
                    joinStr.append(separator);
                }
            }

            return joinStr.toString();
        }
    }

    public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
        return sourceList != null && !sourceList.contains(entry)?sourceList.add(entry):false;
    }

    public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
        if(sourceList != null && !isEmpty(entryList)) {
            int sourceCount = sourceList.size();
            Iterator var3 = entryList.iterator();

            while(var3.hasNext()) {
                Object entry = var3.next();
                if(!sourceList.contains(entry)) {
                    sourceList.add((V) entry);
                }
            }

            return sourceList.size() - sourceCount;
        } else {
            return 0;
        }
    }

    public static <V> int distinctList(List<V> sourceList) {
        if(isEmpty(sourceList)) {
            return 0;
        } else {
            int sourceCount = sourceList.size();
            int sourceListSize = sourceList.size();

            for(int i = 0; i < sourceListSize; ++i) {
                for(int j = i + 1; j < sourceListSize; ++j) {
                    if(sourceList.get(i).equals(sourceList.get(j))) {
                        sourceList.remove(j);
                        sourceListSize = sourceList.size();
                        --j;
                    }
                }
            }

            return sourceCount - sourceList.size();
        }
    }

    public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
        return sourceList != null && value != null?sourceList.add(value):false;
    }

    public static <V> V getLast(List<V> sourceList, V value) {
        return sourceList == null?null: (V) ArrayUtils.getLast(sourceList.toArray(), value, true);
    }

    public static <V> V getNext(List<V> sourceList, V value) {
        return sourceList == null?null: (V) ArrayUtils.getNext(sourceList.toArray(), value, true);
    }

    public static <V> List<V> invertList(List<V> sourceList) {
        if(isEmpty(sourceList)) {
            return sourceList;
        } else {
            ArrayList invertList = new ArrayList(sourceList.size());

            for(int i = sourceList.size() - 1; i >= 0; --i) {
                invertList.add(sourceList.get(i));
            }

            return invertList;
        }
    }
}

