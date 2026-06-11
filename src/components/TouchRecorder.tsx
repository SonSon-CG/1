import React, { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'motion/react';
import { useAppContext } from '../context/AppContext';

export function TouchRecorder() {
  const { isRecording, addRecordedAction, recordedActions } = useAppContext();
  const [clickRipples, setClickRipples] = useState<{id: string, x: number, y: number}[]>([]);

  useEffect(() => {
    if (!isRecording) return;

    const handleGlobalClick = (e: MouseEvent) => {
      // Bỏ qua click vào UI của Widget/Menu bằng cách kiểm tra target
      const target = e.target as HTMLElement;
      if (target.closest('.z-40') || target.closest('.z-50') || target.closest('.z-[60]')) {
        return;
      }

      const x = e.clientX;
      const y = e.clientY;
      
      const newAction = { x, y, timestamp: Date.now() };
      addRecordedAction(newAction);

      const rippleId = Math.random().toString(36).substring(2, 9);
      setClickRipples(prev => [...prev, { id: rippleId, x, y }]);

      // Remove ripple after animation
      setTimeout(() => {
        setClickRipples(prev => prev.filter(r => r.id !== rippleId));
      }, 1000);
    };

    window.addEventListener('click', handleGlobalClick, true);
    return () => window.removeEventListener('click', handleGlobalClick, true);
  }, [isRecording, addRecordedAction]);

  return (
    <div className="fixed inset-0 z-30 pointer-events-none">
      <AnimatePresence>
        {clickRipples.map(ripple => (
          <motion.div
            key={ripple.id}
            initial={{ opacity: 0.8, scale: 0 }}
            animate={{ opacity: 0, scale: 2 }}
            exit={{ opacity: 0 }}
            transition={{ duration: 0.5, ease: "easeOut" }}
            className="absolute w-8 h-8 rounded-full border-2 border-red-500 bg-red-500/20"
            style={{ 
              left: ripple.x - 16, 
              top: ripple.y - 16 
            }}
          />
        ))}
      </AnimatePresence>
      
      {/* Hiển thị danh sách các điểm đã click mờ mờ trên màn hình khi đang REC */}
      {isRecording && recordedActions.map((action, idx) => (
        <div 
          key={action.id}
          className="absolute w-4 h-4 rounded-full bg-red-500/30 border border-red-500 flex items-center justify-center text-[8px] text-white font-bold"
          style={{ 
            left: action.x - 8, 
            top: action.y - 8 
          }}
        >
          {idx + 1}
        </div>
      ))}
    </div>
  );
}
