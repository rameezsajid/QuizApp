package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizapp.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Java");
        addCategory(c1);
        Category c2 = new Category("JavaScript");
        addCategory(c2);
        Category c3 = new Category("Php");
        addCategory(c3);
    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Java: What is the size of float variable?",
                "8 Bit", "16 Bit", "32 Bit (Correct)", 3,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q1);
        Question q2 = new Question("Java: What is the default value of Boolean variable?",
                "true", "false (Correct)", "null", 2,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q2);
        Question q3 = new Question("Java: This is the parent of Error and Exception classes.",
                "Throwable (Correct)", "Catchable", "MainError", 1,
                Question.DIFFICULTY_HARD, Category.JAVA);
        addQuestion(q3);
        Question q4 = new Question("Java: What is the default value of double variable?",
                "0.0d (Correct)", "0", "not defined", 1,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q4);
        Question q5 = new Question("Java: Method Overloading is an example of",
                "Dynamic Binding", "Static Binding (Correct)", "Both", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVA);
        addQuestion(q5);
        Question q6 = new Question("Java: Which of the following is not a keyword in java?",
                "static", "void", "Boolean (Correct)", 3,
                Question.DIFFICULTY_EASY, Category.JAVA);
        addQuestion(q6);
        Question q7 = new Question("PHP: What does PHP stand for?",
                "PHP:Hypertext PreProcessor (Correct)", "Personal Hyper Text", "Private Home Page", 1,
                Question.DIFFICULTY_EASY, Category.PHP);
        addQuestion(q7);
        Question q8 = new Question("PHP: All variables in PHP start with which symbol?",
                "!", "&", "$ (Correct)", 3,
                Question.DIFFICULTY_EASY, Category.PHP);
        addQuestion(q8);
        Question q9 = new Question("PHP: The PHP syntax is most similar to:",
                "JavaScript", "Perl and C (Correct)", "VBScript", 2,
                Question.DIFFICULTY_MEDIUM, Category.PHP);
        addQuestion(q9);
        Question q10 = new Question("PHP: How do you get information from a form that is submitted using the get method?",
                "$_GET[]; (Correct)", "Request.QueryString;", "Request.Form;", 1,
                Question.DIFFICULTY_HARD, Category.PHP);
        addQuestion(q10);
        Question q11 = new Question("PHP: What is the correct way to create a function in PHP?",
                "function myFunction() (Correct)", "create myFunction()", "new_function myFunction()", 1,
                Question.DIFFICULTY_HARD, Category.PHP);
        addQuestion(q11);
        Question q12 = new Question("PHP: Which super global variable holds information about headers, paths, and script locations?",
                "$_SESSION", "$_SERVER (Correct)", "$_GLOBALS", 2,
                Question.DIFFICULTY_MEDIUM, Category.PHP);
        addQuestion(q12);
        Question q13 = new Question("JavaScript: Where is correct place to insert JavaScript",
                "<head> tag", "<body> tag", "both (Correct)", 3,
                Question.DIFFICULTY_EASY, Category.JAVASCRIPT);
        addQuestion(q13);
        Question q14 = new Question("JavaScript: What is the correct way to write if statement",
                "if(i==5) (Correct)", "if i == 5", "if i = 5", 1,
                Question.DIFFICULTY_MEDIUM, Category.JAVASCRIPT);
        addQuestion(q14);
        Question q15 = new Question("PHP: Which of the following type of variables are whole numbers, without a decimal point, like 4195?",
                "Integers (Correct)", "Doubles", "Strings", 1,
                Question.DIFFICULTY_MEDIUM, Category.PHP);
        addQuestion(q15);
        Question q16 = new Question("PHP: Which of the following is correct about constants?",
                "Unlike with variables, you do not need to have a constant with a $", "Only scalar data (boolean, integer, float and string) can be contained in constants.", "Both the Above (Correct)", 3,
                Question.DIFFICULTY_HARD, Category.PHP);
        addQuestion(q16);
        Question q17 = new Question("PHP: Which of the following function is used to get length of a string?",
                "size()", "strlen() (Correct)", "length", 2,
                Question.DIFFICULTY_EASY, Category.PHP);
        addQuestion(q17);
        Question q18 = new Question("PHP: Which of the following variable is used to get user's browser and operating system details in PHP?",
                "HTTP_USER_AGENT (Correct)", "USER", "AGENT", 1,
                Question.DIFFICULTY_HARD, Category.PHP);
        addQuestion(q18);
        Question q19 = new Question("PHP: Which of the following function returns selected parts of an array?",
                "array_reverse()", "array_slice() (Correct)", "array_search()", 2,
                Question.DIFFICULTY_MEDIUM, Category.PHP);
        addQuestion(q19);
        Question q20 = new Question("JavaScript: Which built-in method returns the length of the string?",
                "size()", "length() (Correct)", "index()", 2,
                Question.DIFFICULTY_EASY, Category.JAVASCRIPT);
        addQuestion(q20);
        Question q21 = new Question("JavaScript: Which built-in method returns the calling string value converted to upper case?",
                "changeCase(case)", "toUpper()", "toUpperCase() (Correct)", 3,
                Question.DIFFICULTY_EASY, Category.JAVASCRIPT);
        addQuestion(q21);
        Question q22 = new Question("JavaScript: Which of the following function of String object extracts a section of a string and returns a new string?",
                "replace()", "split()", "slice() (Correct)", 3,
                Question.DIFFICULTY_MEDIUM, Category.JAVASCRIPT);
        addQuestion(q22);
        Question q23 = new Question("JavaScript: Which of the following function of String object returns the calling string value converted to upper case?",
                "toUpperCase() (Correct)", "toLocaleUpperCase()", "substring()", 1,
                Question.DIFFICULTY_HARD, Category.JAVASCRIPT);
        addQuestion(q23);
        Question q24 = new Question("JavaScript: Which of the following function of String object causes a string to be displayed in a small font, as if it were in a <small> tag?",
                "link()", "small() (Correct)", "sub()", 2,
                Question.DIFFICULTY_MEDIUM, Category.JAVASCRIPT);
        addQuestion(q24);
        Question q25 = new Question("JavaScript: Which of the following function of Array object adds one or more elements to the end of an array and returns the new length of the array?",
                "pop", "push (Correct)", "join", 2,
                Question.DIFFICULTY_HARD, Category.JAVASCRIPT);
        addQuestion(q25);
        Question q26 = new Question("JavaScript: Which of the following function of Array object reverses the order of the elements of an array?",
                "reduce()", "push()", "reverse() (Correct)", 3,
                Question.DIFFICULTY_HARD, Category.JAVASCRIPT);
        addQuestion(q26);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}