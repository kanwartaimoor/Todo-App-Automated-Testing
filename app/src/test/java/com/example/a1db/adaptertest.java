//package com.example.a1db;
//
//import android.content.Context;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import org.junit.Test;
//import com.example.a1db.*;
//import java.util.ArrayList;
//
//import static java.security.AccessController.getContext;
//
//
//
//
//public class adaptertest  extends junit.framework.TestCase{
//        private TodoListAdapter mAdapter;
//note_list_item a;
//        private Todo one;
//        private Todo two;
//
//        public adaptertest() {
//            super();
//        }
//
//        @Test
//        protected void setUp() throws Exception {
//            super.setUp();
//            ArrayList<Todo> data = new ArrayList<Todo>();
//
//            one = new Todo("Todo 1","sample test","high","20th nov","11:11",20);
//            two = new Todo("Todo 2","sample test 2","low","20th nov","11:11",90);
//            data.add(one);
//            data.add(two);
//            mAdapter = new TodoListAdapter(a.getApplicationContext(),data);
//        }
//
//    @Test
//        public void testGetItem() {
//            assertEquals("Todo 1 was expected.", one.getTodo(),
//                    ((Todo) mAdapter.getItem(0)).getTodo());
//        }
//    @Test
//        public void testGetItemId() {
//            assertEquals("Wrong ID.", 0, mAdapter.getItemId(0));
//        }
//
//        public void testGetCount() {
//            assertEquals("Todo amount incorrect.", 2, mAdapter.getCount());
//        }
//
//    @Test
//        // I have 3 views on my adapter, name, number and photo
//        public void testGetView() {
//            View view = mAdapter.getView(0, null, null);
//
//            TextView title = (TextView) view
//                    .findViewById(R.id.EventName);
//
//            TextView priority = (TextView) view
//                    .findViewById(R.id.Priority);
//        TextView date = (TextView) view
//                .findViewById(R.id.DateTime);
//
//
//
//            //On this part you will have to test it with your own views/data
//            assertNotNull("View is null. ", view);
//            assertNotNull("Title TextView is null. ", title);
//            assertNotNull("Priority TextView is null. ", priority);
//            assertNotNull("Date TextView is null. ", date);
//
//            assertEquals("Titles doesn't match.", one.getTodo(), title.getText());
//            assertEquals("Priority doesn't match.", one.getPriority(),
//                    priority.getText());
//        }
//    }
//
