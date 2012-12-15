
#include <windows.h>
#include <string>
#include <vector>
#include <psapi.h>

#define MAX_FILE_PATH 260

long GetForeGroundWindow()
{
	return (long)GetForegroundWindow();
}

int GetWindowProcessId(long hwnd)
{
  DWORD lpdwProcessId;
  GetWindowThreadProcessId((HWND)hwnd, &lpdwProcessId);
  return lpdwProcessId;
}

std::string GetProcessName(int pid)
{
  HANDLE proccessHandle = OpenProcess(PROCESS_QUERY_INFORMATION, false, pid);

  std::vector<char> buffer(MAX_FILE_PATH, 0);
  GetProcessImageFileNameA(proccessHandle, &buffer.front(), MAX_FILE_PATH);
  std::string path(buffer.begin(), buffer.end());
  
  return path;
}

/*  
  std::cout << foreGroundWind << std::endl;

  DWORD lpdwProcessId;
  GetWindowThreadProcessId(foreGroundWind, &lpdwProcessId);
  std::cout << lpdwProcessId << std::endl;

  HANDLE proccessHandle = OpenProcess(PROCESS_QUERY_INFORMATION, false, lpdwProcessId);
  std::cout << proccessHandle << std::endl;

  std::vector<wchar_t> buffer(MAX_FILE_PATH, 0);
  GetProcessImageFileNameW(proccessHandle, &buffer.front(), MAX_FILE_PATH);
  std::wstring path(buffer.begin(), buffer.end());

  std::generate(buffer.begin(), buffer.end(), []() { return 0; });
  GetWindowTextW(foreGroundWind, &buffer.front(), MAX_FILE_PATH);
  std::wstring windowTitle(buffer.begin(), buffer.end());
*/
int GetIdleSec()
{
  DWORD tickCount, idleCount;
  LASTINPUTINFO lif;
  lif.cbSize = sizeof(LASTINPUTINFO);

  GetLastInputInfo(&lif);
  tickCount = GetTickCount();
  idleCount = (tickCount - lif.dwTime) / 1000;
  
  return idleCount;
}
