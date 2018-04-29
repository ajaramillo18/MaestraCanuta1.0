package com.jama.maestracanuta10.dummy;

import com.jama.maestracanuta10.App;
import com.jama.maestracanuta10.data.DatabaseHandler;
import com.jama.maestracanuta10.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace this class for a proper StudentContent class in the future.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static DatabaseHandler db = new DatabaseHandler(App.context);
    public static List<Student> studentList = db.getAllStudents();

    private static final int COUNT = db.getStudentsCount();

    static {
        // Add some sample items.
        for (int i = 0; i <= COUNT-1; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        Student st = studentList.get(position);
        return new DummyItem(st.getId(), st.getName(), "Tutor: "+ st.getTutorName()+ "\n  Cel: "+ st.getTutorPhoneNumber());
    }

/*    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }*/

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
