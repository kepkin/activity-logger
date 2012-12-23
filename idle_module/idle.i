/* File : idle.i */
%module idle

%include "idle.h"

%inline %{
#include "idle.h"
extern WinHelper* CreateWinHelper();
extern int GetIdleTime();
%}


