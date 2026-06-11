import { useState, useEffect } from 'react';
import { PermissionPopup } from './components/PermissionPopup';
import { FloatingWidget } from './components/FloatingWidget';
import { AppProvider } from './context/AppContext';
import { RecordingOverlay } from './components/RecordingOverlay';
import { ToastProvider } from './context/ToastContext';
import { TouchRecorder } from './components/TouchRecorder';

type PermissionStatus = 'idle' | 'requesting' | 'granted' | 'denied';

export default function App() {
  const [permissionStatus, setPermissionStatus] = useState<PermissionStatus>('idle');
  const [isRpaEnabled, setIsRpaEnabled] = useState(false);

  useEffect(() => {
    // Simulate checking permissions on app start
    const timer = setTimeout(() => {
      setPermissionStatus('requesting');
    }, 500);
    return () => clearTimeout(timer);
  }, []);

  const handleGrant = () => {
    setPermissionStatus('granted');
    setIsRpaEnabled(true);
  };

  const handleDeny = () => {
    setPermissionStatus('denied');
    setIsRpaEnabled(false);
  };

  const handleAcknowledgeResult = () => {
    setPermissionStatus('idle');
  };

  return (
    <ToastProvider>
      <AppProvider>
        <div className="min-h-screen bg-gray-50 relative overflow-hidden font-sans">
          {/* Simulation Background */}
          <div className="absolute inset-0 flex items-center justify-center opacity-30 pointer-events-none">
            <h1 className="text-4xl font-bold text-gray-300 tracking-wider">
              Màn Hình Ứng Dụng Nền
            </h1>
          </div>

          <PermissionPopup 
            status={permissionStatus} 
            onGrant={handleGrant} 
            onDeny={handleDeny} 
            onAcknowledgeResult={handleAcknowledgeResult}
          />

          <FloatingWidget isRpaEnabled={isRpaEnabled} />
          <RecordingOverlay />
          <TouchRecorder />
        </div>
      </AppProvider>
    </ToastProvider>
  );
}
