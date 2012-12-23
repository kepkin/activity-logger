#include <windows.h>
#include "idle_win.h"

WindowHelper::WindowHelper()
  : WinHelper(NULL)
{
}

long WindowHelper::GetWindowPid(long w)
{
  DWORD lpdwProcessId;
  GetWindowThreadProcessId((HWND) w, &lpdwProcessId);
  return lpdwProcessId;

}

long WindowHelper::GetForeGroundWindow()
{
  return (long) GetForegroundWindow();
}

int GetIdleTime()
{
  DWORD tickCount, idleCount;
  LASTINPUTINFO lif;
  lif.cbSize = sizeof(LASTINPUTINFO);

  GetLastInputInfo(&lif);
  tickCount = GetTickCount();
  idleCount = (tickCount - lif.dwTime) / 1000;

  return idleCount;
}

WinHelper* CreateWinHelper()
{
  WinHelper* impl = new WindowHelper();
  return new WinHelper(impl);
}
