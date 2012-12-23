#include <string>
#include <sstream>

#include "idle_lin.h"

#define min(A,B) (A) < (B) ? (A) : (B);

namespace
{
  char* Get_Window_Property_Data_And_Type(Display* dpy, Window win, Atom atom, long *length,
    Atom *type, int *size)
  {
    Atom actual_type;
    int actual_format;
    unsigned long nitems;
    unsigned long nbytes;
    unsigned long bytes_after;
    unsigned char *prop;
    int status;

    status = XGetWindowProperty(dpy, win, atom, 0, (~0L), False, AnyPropertyType, &actual_type,
      &actual_format, &nitems, &bytes_after, &prop);
    if (status == BadWindow)
    {
      //std::cout << "window id # " << win << " does not exists!" << std::endl;
    }
    if (status != Success)
    {
      //std::cout << "XGetWindowProperty failed!" << std::endl;
    }

    if (actual_format == 32)
      nbytes = sizeof(long);
    else if (actual_format == 16)
      nbytes = sizeof(short);
    else if (actual_format == 8)
      nbytes = 1;
    else if (actual_format == 0)
      nbytes = 0;
    else
    {
      //std::cout << "abort" << std::endl;
    }
    *length = min(nitems * nbytes, (~0L));
    *type = actual_type;
    *size = actual_format;
    return (char *) prop;
  }

  long GetForeGroundWindowId(Display* display, const char* property, Window w)
  {
    Atom pidAtom = XInternAtom(display, property/*"_NET_ACTIVE_WINDOW"/*"_NET_WM_PID"*/, true);

    Atom actual_type;
    int size;
    long length;
    char* data = Get_Window_Property_Data_And_Type(display, w, pidAtom, &length, &actual_type,
      &size);

    if (!size)
    {
      return 0;
    }

    long result;
    if (size == 32)
    {
      result = *((long *) data);
    }
    else if (size == 8)
    {
      result = *((char *) data);
    }
    else
    {
      result = size;
    }
    XFree(data);
    return result;
  }
}

WindowHelper::WindowHelper()
  : WinHelper(NULL)
{
  Dpy = XOpenDisplay(NULL);
}

long WindowHelper::GetForeGroundWindow()
{
  return GetForeGroundWindowId(Dpy, "_NET_ACTIVE_WINDOW", DefaultRootWindow(Dpy));
}

long WindowHelper::GetWindowPid(long w)
{
  return GetForeGroundWindowId(Dpy, "_NET_WM_PID", w);
}

WindowHelper::~WindowHelper()
{
  XCloseDisplay(Dpy);
}

int GetIdleTime()
{
  XScreenSaverInfo *info = XScreenSaverAllocInfo();
  Display *display = XOpenDisplay(0);

  XScreenSaverQueryInfo(display, DefaultRootWindow(display), info);
  XCloseDisplay(display);
  return info->idle;
}

WinHelper* CreateWinHelper()
{
  WinHelper* impl = new WindowHelper();
  return new WinHelper(impl);
}
