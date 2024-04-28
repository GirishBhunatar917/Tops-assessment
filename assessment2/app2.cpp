#include<iostream>
using namespace std;
class lecture{
    string lecturer_name;
    string subject_name;
    string course_name;
    public:
    lecture(string lecturer,string subject,string course)
    {
        lecturer_name=lecturer;
        subject_name=subject;
        course_name=course;
    }
    void display()
    {
        cout<<"\nyour lecturer name is:"<<lecturer_name<<"\nyour subject is:"<<subject_name<<"\nyour course is:"<<course_name;
    }
};

int main()
{
    
    int number_of_lectures,i;
    string lecturer,subject,course;

    cout<<"\nEnter the number of lectures :";
    cin>>number_of_lectures;

    for(i=1;i<=number_of_lectures;i++)
    {
        cout<<"\n-----------------------\n";
        cout<<"\nEnter lecture details:";
        cout<<"\n-----------------------\n";
        cout<<"\nEnter lecturer name:";
        cin>>lecturer;
        cout<<"\nEnter subject name:";
        cin>>subject;;
        cout<<"\nEnter course name:";
        cin>>course;
    }
    for(i=1;i<=number_of_lectures;i++)
    {   
        cout<<"\n---------------------\n";
        cout<<"lecture-"<<i<<" details";
        cout<<"\n---------------------\n";

        lecture obj(lecturer,subject,course);
        obj.display();
    }
    
    return 0;
}