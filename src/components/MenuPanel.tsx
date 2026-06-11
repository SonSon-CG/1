import React, { useState } from 'react';
import { Play, Circle, X, Square } from 'lucide-react';
import { cn } from '../lib/utils';
import { useAppContext, FormData } from '../context/AppContext';
import { useToast } from '../context/ToastContext';
import { androidBridge } from '../lib/androidBridge';

// --- Thành phần nút Macro ---
interface MacroButtonProps {
  id: string;
  title: string;
  hasRpa?: boolean;
  baseClass?: string;
  activeMacro: string | null;
  setActiveMacro: (id: string | null) => void;
  popoverPosition?: 'top' | 'bottom';
  onClose: () => void;
}

function MacroButton({ id, title, hasRpa, baseClass, activeMacro, setActiveMacro, popoverPosition = 'top', onClose }: MacroButtonProps) {
  const isActive = activeMacro === id;
  const { macroConfigs, updateMacroConfig, setIsRecording, setIsPlaying, clearRecordedActions } = useAppContext();
  const { showToast } = useToast();
  const config = macroConfigs[id] || { rpaEnabled: true };

  const handleClick = () => {
    setActiveMacro(isActive ? null : id);
  };

  const handlePopoverRec = () => {
    setIsPlaying(false);
    setIsRecording(true);
    clearRecordedActions();
    androidBridge.startRecording(id);
    showToast(`Bắt đầu ghi macro ${id.toUpperCase()}`, 'info');
    onClose();
  };

  const handlePopoverPlay = () => {
    setIsRecording(false);
    setIsPlaying(true);
    androidBridge.startPlaying(id, config.rpaEnabled, {});
    showToast(`Bắt đầu chạy macro ${id.toUpperCase()}`, 'success');
    onClose();
  };

  return (
    <div className="relative flex justify-center">
      <button
        onClick={handleClick}
        className={cn(
          "w-full h-10 rounded-lg flex items-center justify-center font-bold text-[#e14f4f] text-sm shadow-sm transition-all active:scale-95",
          baseClass || "bg-gray-200 hover:bg-gray-300",
          isActive ? "ring-2 ring-blue-400 ring-offset-1" : ""
        )}
      >
        {title}
      </button>

      {/* Popover Menu khi click vào icon chữ đỏ */}
      {isActive && (
        <div className={cn(
          "absolute z-50 flex gap-1 bg-white/50 backdrop-blur-md p-1.5 rounded-xl shadow-lg border border-gray-200",
          popoverPosition === 'top' ? "-top-12" : "top-12 right-0"
        )}>
          <button onClick={handlePopoverRec} className="flex items-center justify-center gap-1 px-3 py-1 bg-[#c5abf1] hover:bg-[#b08dee] rounded-lg text-xs font-bold text-gray-800 transition-colors">
            REC
          </button>
          <button onClick={handlePopoverPlay} className="flex items-center justify-center gap-1 px-3 py-1 bg-[#c5abf1] hover:bg-[#b08dee] rounded-lg text-xs font-bold text-gray-800 transition-colors whitespace-nowrap">
            PLAY
          </button>
          {hasRpa && (
            <label className="flex items-center gap-2 px-3 py-1 bg-[#c5abf1] rounded-lg cursor-pointer text-xs font-bold text-gray-800 hover:bg-[#b08dee] transition-colors">
              RPA
              <input 
                type="checkbox" 
                checked={config.rpaEnabled}
                onChange={(e) => updateMacroConfig(id, { rpaEnabled: e.target.checked })}
                className="w-4 h-4 rounded text-green-500 border-gray-300 focus:ring-green-500" 
              />
            </label>
          )}
        </div>
      )}
    </div>
  );
}

// --- Thành phần Input Form Dữ liệu ---
function DataInput({ label, value, onChange }: { label: string, value: string, onChange: (val: string) => void }) {
  return (
    <div className="flex h-8 rounded-full overflow-hidden shadow-sm border border-gray-100">
      <div className="bg-[#a7f3d0] px-3 py-1 text-[11px] font-bold text-gray-700 w-24 flex items-center justify-center whitespace-nowrap">
        {label}
      </div>
      <input
        type="text"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        className="bg-[#fef08a] px-3 py-1 flex-1 text-xs text-gray-800 outline-none w-full"
      />
    </div>
  );
}

// --- MAIN MENU PANEL ---
export function MenuPanel({ onClose }: { onClose: () => void }) {
  const { 
    activeMacro, setActiveMacro, 
    isPlaying, setIsPlaying, 
    isRecording, setIsRecording,
    formData, updateFormData,
    clearRecordedActions
  } = useAppContext();
  
  const { showToast } = useToast();

  // Focus mode cho popup: Tự ẩn popup khi click ra ngoài vùng menu (Giả lập đơn giản)
  const handleScrollOrInteraction = () => {
    // setActiveMacro(null); // Bỏ comment nếu muốn auto-hide popup khi cuộn
  };

  const handleSaveBtn = () => {
    androidBridge.saveConfig({ formData, macroConfigs });
    showToast("Đã lưu lại cấu hình và quy trình hiện tại!", 'success');
  };

  const handleStopBtn = () => {
    setIsPlaying(false);
    setIsRecording(false);
    androidBridge.stopPlaying();
    androidBridge.stopRecording();
    showToast("Đã dừng mọi hoạt động", 'info');
  };

  const handlePauseBtn = () => {
    androidBridge.pauseAction();
    showToast("Tạm dừng hoạt động", 'info');
  };

  const handlePlayGlobal = () => {
    setIsPlaying(true);
    setIsRecording(false);
    androidBridge.startPlaying('general', true, formData);
    showToast("Chạy quy trình chung", 'success');
    onClose();
  };

  const handleRecGlobal = () => {
    setIsRecording(!isRecording);
    setIsPlaying(false);
    
    if (!isRecording) {
      clearRecordedActions();
      androidBridge.startRecording('general');
      onClose();
    } else {
      androidBridge.stopRecording();
    }
  };

  return (
    <div 
      className="absolute left-16 top-0 bg-white rounded-2xl shadow-2xl w-[400px] border border-gray-200 flex flex-col"
      onScroll={handleScrollOrInteraction}
    >
      {/* Header */}
      <div className="flex items-center justify-between p-4 border-b border-gray-100">
        <h2 className="text-xl font-bold text-gray-800 tracking-wider">MENU</h2>
        <button onClick={onClose} className="p-1 text-gray-400 hover:text-gray-700 rounded-full hover:bg-gray-100 transition-colors">
          <X size={20} />
        </button>
      </div>

      <div className="p-4 max-h-[75vh] overflow-y-visible overflow-x-hidden space-y-4">
        {/* Hàng 1: Nút điều khiển chung */}
        <div className="flex gap-2 relative">
          <button 
            onClick={handleRecGlobal}
            className={cn(
              "flex-1 py-2 rounded-lg text-sm font-bold transition-colors",
              isRecording ? "bg-red-200 text-red-700 ring-2 ring-red-400" : "bg-gray-200 hover:bg-gray-300 text-gray-600"
            )}
          >
            REC
          </button>
          <button onClick={handlePauseBtn} className="flex-1 py-2 bg-gray-200 hover:bg-gray-300 rounded-lg text-sm font-bold text-gray-600 transition-colors">PAUSE</button>
          <button onClick={handleSaveBtn} className="flex-1 py-2 bg-gray-200 hover:bg-gray-300 rounded-lg text-sm font-bold text-gray-600 transition-colors">SAVE</button>
          <div className="flex-1 relative z-20">
            <MacroButton id="socks" title="SOCKS" hasRpa activeMacro={activeMacro} setActiveMacro={setActiveMacro} popoverPosition="bottom" onClose={onClose} />
          </div>
        </div>

        {/* Hàng 2: PLAY, STOP, Bank */}
        <div className="flex gap-2">
          <button 
            onClick={handlePlayGlobal}
            className="flex-[2] py-2 bg-gray-300 hover:bg-gray-400 rounded-lg text-xl font-black text-gray-800 flex items-center justify-center gap-2 transition-colors">
            PLAY <Play fill="#ec4899" className="text-pink-500" />
          </button>
          <button 
            onClick={handleStopBtn}
            className="flex-[1.2] py-2 bg-gray-300 hover:bg-gray-400 rounded-lg text-sm font-bold text-[#e14f4f] flex items-center justify-center gap-1 transition-colors">
             STOP
          </button>
          <div className="flex-[1]"><MacroButton id="bank1" title="Bank 1" activeMacro={activeMacro} setActiveMacro={setActiveMacro} onClose={onClose} /></div>
          <div className="flex-[1]"><MacroButton id="bank2" title="Bank 2" activeMacro={activeMacro} setActiveMacro={setActiveMacro} onClose={onClose} /></div>
        </div>

        {/* Khối Macros: REG, KM, FARM */}
        <div className="flex gap-3 mt-4">
          <div className="flex-1 space-y-3">
            {/* REG Box */}
            <div className="flex bg-[#f3e8fa] rounded-xl p-2 gap-2 shadow-inner">
              <div className="flex flex-col justify-center items-center font-black text-xl text-[#b581e2] w-6 tracking-widest">
                <span>R</span><span>E</span><span>G</span>
              </div>
              <div className="grid grid-cols-4 gap-2 flex-1 relative">
                {['R1', 'R2', 'R3', 'R4', 'R5', 'R6', 'R7', 'R8'].map(id => (
                  <MacroButton key={id} id={id} title={id} hasRpa baseClass="bg-[#e4ccf5] hover:bg-[#d0abed]" activeMacro={activeMacro} setActiveMacro={setActiveMacro} onClose={onClose} />
                ))}
              </div>
            </div>

            {/* KM Box */}
            <div className="flex bg-[#fef5d5] rounded-xl p-2 gap-2 shadow-inner">
              <div className="flex flex-col justify-center items-center font-black text-xl text-[#dcb14b] w-6 tracking-widest">
                <span>K</span><span>M</span>
              </div>
              <div className="grid grid-cols-4 gap-2 flex-1 relative">
                {['K1', 'K2', 'K3', 'K4', 'K5', 'K6', 'K7', 'K8'].map(id => (
                  <MacroButton key={id} id={id} title={id} hasRpa baseClass="bg-[#f2dfa6] hover:bg-[#eed07a]" activeMacro={activeMacro} setActiveMacro={setActiveMacro} onClose={onClose} />
                ))}
              </div>
            </div>
          </div>

          {/* FARM Box */}
          <div className="w-[88px] bg-[#d5e7f8] rounded-xl p-2 flex flex-col gap-2 shadow-inner relative">
            {['FARM 1', 'FARM 2', 'FARM 3', 'FARM 4', 'FARM 5'].map(id => (
              <MacroButton key={id} id={id} title={id} baseClass="bg-[#b4d2ee] hover:bg-[#97bfe7]" activeMacro={activeMacro} setActiveMacro={setActiveMacro} onClose={onClose} />
            ))}
          </div>
        </div>

        <div className="divider h-px bg-gray-200 my-4" />

        {/* Vùng Data Input */}
        <div className="grid grid-cols-2 gap-2 pb-2">
          <DataInput label="Server IP" value={formData.serverIp} onChange={(val) => updateFormData('serverIp', val)} />
          <DataInput label="Server Port" value={formData.serverPort} onChange={(val) => updateFormData('serverPort', val)} />
          <DataInput label="Username" value={formData.username} onChange={(val) => updateFormData('username', val)} />
          <DataInput label="Password" value={formData.password} onChange={(val) => updateFormData('password', val)} />
          <DataInput label="Tên tài khoản" value={formData.accountName} onChange={(val) => updateFormData('accountName', val)} />
          <DataInput label="Mật khẩu" value={formData.accountPass} onChange={(val) => updateFormData('accountPass', val)} />
          <DataInput label="SĐT" value={formData.phone} onChange={(val) => updateFormData('phone', val)} />
          <DataInput label="Họ và Tên" value={formData.fullName} onChange={(val) => updateFormData('fullName', val)} />
        </div>
        <div className="text-center">
            <span className="text-[10px] font-medium text-gray-500 uppercase tracking-wide">
              Phần xanh lá và vàng là nơi nhập data
            </span>
        </div>
      </div>
    </div>
  );
}
