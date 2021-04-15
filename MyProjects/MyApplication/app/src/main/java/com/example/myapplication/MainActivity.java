package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static android.view.Gravity.apply;

public class MainActivity extends AppCompatActivity {

//    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, SendActivity.class));
        // Create an italic "hello, " a red "world",
        // and bold the entire sequence.
 //       text = (String) bold(italic(getString(R.string.about_robotics)),
   //             color(Color.RED, getString(R.string.about_robotics)));
        //            </pre>
    //</section><section><h3 id="java">Java</h3>
   // <pre class="prettyprint lang-java">
    // Create an italic "hello, " a red "world",
    // and bold the entire sequence.
     //    CharSequence text = bold(italic(getString(R.string.hello)),
      //   color(Color.RED, getString(R.string.world)));

    }

    /**
     * Returns a CharSequence that concatenates the specified array of CharSequence
     * objects and then applies a list of zero or more tags to the entire range.
     *
  //   * @param content an array of character sequences to apply a style to
 //    * @param tags the styled span objects to apply to the content
     *        such as android.text.style.StyleSpan
     *
     */
    /*private static CharSequence applyStyles(CharSequence[] content, Object[] tags) {
        SpannableStringBuilder text = new SpannableStringBuilder();
        openTags(text, tags);
        for (CharSequence item : content) {
            text.append(item);
        }
        closeTags(text, tags);
        return text;
    }

    /**
     * Iterates over an array of tags and applies them to the beginning of the specified
     * Spannable object so that future text appended to the text will have the styling
     * applied to it. Do not call this method directly.
     *
    private static void openTags(Spannable text, Object[] tags) {
        for (Object tag : tags) {
            text.setSpan(tag, 0, 0, Spannable.SPAN_MARK_MARK);
        }
    }

    /**
     * "Closes" the specified tags on a Spannable by updating the spans to be
     * endpoint-exclusive so that future text appended to the end will not take
     * on the same styling. Do not call this method directly.
     *
    private static void closeTags(Spannable text, Object[] tags) {
        int len = text.length();
        for (Object tag : tags) {
            if (len > 0) {
                text.setSpan(tag, 0, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                text.removeSpan(tag);
            }
        }
    }*/

    /*
     * Returns a CharSequence that applies boldface to the concatenation
     * of the specified CharSequence objects.
     *
   // public static CharSequence bold(CharSequence... content) {
   //     return applyStyles(content, new StyleSpan[]{new StyleSpan(Typeface.BOLD)});
    }*

    /**
     * Returns a CharSequence that applies italics to the concatenation
     * of the specified CharSequence objects.
     *
    public static CharSequence italic(CharSequence... content) {
        return applyStyles(content, new StyleSpan[]{new StyleSpan(Typeface.ITALIC)});
    }

    /**
     * Returns a CharSequence that applies a foreground color to the
     * concatenation of the specified CharSequence objects.
     *
    public static CharSequence color(int color, CharSequence... content) {
        return applyStyles(content, new ForegroundColorSpan[]{new ForegroundColorSpan(color)});
    }*/


}