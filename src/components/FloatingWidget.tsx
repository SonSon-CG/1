import React, { useState } from 'react';
import { motion, AnimatePresence } from 'motion/react';
import { Settings, Play, Pause, Square } from 'lucide-react';
import { cn } from '../lib/utils';
import { MenuPanel } from './MenuPanel';
import { useAppContext } from '../context/AppContext';

import { androidBridge } from '../lib/androidBridge';

interface FloatingWidgetProps {
  isRpaEnabled: boolean;
}

export function FloatingWidget({ isRpaEnabled }: FloatingWidgetProps) {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const { isPlaying, setIsPlaying, isRecording, setIsRecording } = useAppContext();

  // Based on requirement:
  // "Nút nổi khi không sử dụng Transparency 68%"
  // "Nút nổi khi ấn nút Play Transparency 100%"
  const opacityClass = (isPlaying || isRecording) ? 'opacity-100' : 'opacity-[0.68] hover:opacity-100';

  const handleWidgetPlay = () => {
    setIsPlaying(true);
    androidBridge.startPlaying('general', true, {}); 
  };

  const handleWidgetPause = () => {
    setIsPlaying(false);
    setIsRecording(false);
    androidBridge.pauseAction();
  };

  return (
    <motion.div 
      className="fixed z-40"
      initial={{ top: 80, left: 16 }}
      drag={!isMenuOpen}
      dragMomentum={false}
    >
      <AnimatePresence>
        <div 
          className={cn("flex items-start gap-2 transition-opacity duration-300", opacityClass)}
        >
          {/* Main Logo Button */}
          <button 
            onClick={() => setIsMenuOpen(!isMenuOpen)}
            className="w-14 h-14 bg-[#9d2b2b] rounded-2xl flex shrink-0 items-center justify-center shadow-lg border-2 border-white/20 active:scale-95 transition-transform"
          >
            {/* Custom geometric logo representation based on the image */}
            <div className="relative w-8 h-8 flex items-center justify-center">
              <div className="absolute inset-0 border-[3px] border-[#ecd58e] rotate-45 rounded-sm"></div>
              <div className="absolute inset-0 border-[3px] border-[#ecd58e] rounded-sm"></div>
              <div className="w-2 h-2 bg-[#ecd58e] rounded-full"></div>
            </div>
          </button>

          {/* Collapsed quick actions (Play/Pause) when menu is closed */}
          {!isMenuOpen && (
            <motion.div 
              initial={{ opacity: 0, x: -10 }}
              animate={{ opacity: 1, x: 0 }}
              exit={{ opacity: 0, x: -10 }}
              className="flex items-center gap-2 bg-gray-300 rounded-r-2xl pr-3 transition-colors h-14"
            >
              {(!isPlaying && !isRecording) ? (
                <button 
                  onClick={handleWidgetPlay}
                  className="w-12 h-12 flex items-center justify-center text-pink-500 hover:scale-110 transition-transform"
                >
                  <Play fill="currentColor" size={32} />
                </button>
              ) : (
                <button 
                  onClick={handleWidgetPause}
                  className="w-12 h-12 flex items-center justify-center text-[#c24c4c] hover:scale-110 transition-transform"
                >
                  <Pause fill="currentColor" size={32} />
                </button>
              )}
            </motion.div>
          )}

          {/* Expanded Menu Panel */}
          {isMenuOpen && (
            <motion.div
              initial={{ opacity: 0, x: -20, scale: 0.95 }}
              animate={{ opacity: 1, x: 0, scale: 1 }}
              exit={{ opacity: 0, x: -20, scale: 0.95 }}
              className="relative"
            >
              <MenuPanel onClose={() => setIsMenuOpen(false)} />
            </motion.div>
          )}
        </div>
      </AnimatePresence>
    </motion.div>
  );
}

