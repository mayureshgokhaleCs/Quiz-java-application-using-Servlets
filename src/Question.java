
public class Question {
int id;
String question;
String option1;
String option2;
String option3;
String answer;
Question(int id,String question,String option1,String option2,String option3,String answer){
this.id=id;
this.question=question;
this.option1=option1;
this.option2=option2;
this.option3=option3;
this.answer=answer;
}
public String getQuestion() {
	return question;
}
public String getOption1() {
	return option1;
}
public String getOption2() {
	return option2;
}
public String getOption3() {
	return option3;
}
public String getAnswer() {
	return answer;
}
public int getId() {
	return id;
}
}
