/**
@file
@brief
@details
@author   aln (aln@acronis.com)
@since    $Id: enterprise_sequence_factory.cpp 538224 2011-03-02 12:24:08Z Alexander.Nevskiy $
*/

#ifndef IDLE_H_
#define IDLE_H_

class WinHelper
{
public:
  WinHelper(WinHelper *pimpl);

  virtual long GetWindowPid(long w);
  virtual long GetForeGroundWindow();

  virtual ~WinHelper()
  {
  }
private:
  WinHelper *Pimpl;
};

//WinHelper* CreateWinHelper();

#endif
