End points:-
1) comparing the address books, where north ,south is csv file name
http://localhost:8080/compare/north/south
2) creating /appending address book:- http://localhost:8080/save?address=north where address denotes the address book name 
sample payload:-
{
  "name": "golu",
   "phone": "9953290636"
 }
        
3) getting the list of contacts in a sorted order of name:-
http://localhost:8080/getDetail/north
