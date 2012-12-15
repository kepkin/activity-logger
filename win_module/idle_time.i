/* File : idle_time.i */
%module idle_time

%include <std_string.i>

%inline %{
extern long GetForeGroundWindow();
extern int GetWindowProcessId(long hwnd);
extern int GetIdleSec();
std::string GetProcessName(int pid);
%}
